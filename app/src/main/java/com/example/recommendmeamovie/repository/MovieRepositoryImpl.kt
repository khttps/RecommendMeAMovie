package com.example.recommendmeamovie.repository

import androidx.room.withTransaction
import com.example.recommendmeamovie.source.local.MovieDatabase
import com.example.recommendmeamovie.source.local.asMovieDomain
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.source.remote.asEntity
import com.example.recommendmeamovie.util.networkBoundResource
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl
@Inject constructor(
    private val movieService: MovieApiService,
    private val movieDatabase: MovieDatabase
) : MovieRepository {

    private val movieDao = movieDatabase.movieDao

    companion object {
        private const val POPULAR_FILTER = "popular"
        private const val TOP_RATED_FILTER = "top_rated"
    }

    override fun getPopularMovies() = getCachedMovies(POPULAR_FILTER)
    override fun getTopRatedMovies() = getCachedMovies(TOP_RATED_FILTER)

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
}
