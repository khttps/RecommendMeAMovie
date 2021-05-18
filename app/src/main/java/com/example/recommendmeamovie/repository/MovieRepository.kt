package com.example.recommendmeamovie.repository

import androidx.room.withTransaction
import com.example.recommendmeamovie.source.local.MovieDatabase
import com.example.recommendmeamovie.source.local.asMovieDomain
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.source.remote.asEntity
import com.example.recommendmeamovie.util.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository
@Inject constructor(
    private val movieService: MovieApiService,
    private val movieDatabase: MovieDatabase
) {

    private val movieDao = movieDatabase.movieDao

    companion object {
        private const val POPULAR_FILTER = "popular"
        private const val TOP_RATED_FILTER = "top_rated"
    }

    val popularMovies = getCachedMovies(POPULAR_FILTER)

    val topRatedMovies = getCachedMovies(TOP_RATED_FILTER)

    private fun getCachedMovies(filter : String) = networkBoundResource(
        query = {
            movieDao.getMovies(filter).map { it.asMovieDomain() }
        },
        fetch = {
            movieService.getMovies(filter)
        },
        saveFetchResult = {
            movieDatabase.withTransaction {
                movieDao.deleteMovies(filter)
                movieDao.addMovieList(it.asEntity(filter))
            }
        }
    )

    suspend fun refreshCacheData() {
        withContext(Dispatchers.IO) {
            val popularMoviesCache = movieService.getMovies(POPULAR_FILTER)
            movieDao.addMovieList(popularMoviesCache.asEntity(POPULAR_FILTER))

            val topRatedMoviesCache = movieService.getMovies(TOP_RATED_FILTER)
            movieDao.addMovieList(topRatedMoviesCache.asEntity(TOP_RATED_FILTER))
        }
    }
}
