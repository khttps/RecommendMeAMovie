package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.source.local.MovieDao
import com.example.recommendmeamovie.source.local.asDomain
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.source.remote.asEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository
@Inject constructor(
    private val movieService : MovieApiService,
    private val movieDao: MovieDao) {

    companion object {
        private const val POPULAR_FILTER = "popular"
        private const val TOP_RATED_FILTER = "top_rated"
    }

    suspend fun getPopularMovies() = flow {
        val popularMovies = movieDao.getMovies(POPULAR_FILTER).asDomain()
        emit(popularMovies)
    }.flowOn(Dispatchers.IO)

    suspend fun getTopRatedMovies() = flow {
        val topRatedMovies = movieDao.getMovies(TOP_RATED_FILTER).asDomain()
        emit(topRatedMovies)
    }.flowOn(Dispatchers.IO)

    suspend fun refreshCacheData() {
        withContext(Dispatchers.IO) {
            val popularMoviesCache = movieService.getMovies(POPULAR_FILTER)
            movieDao.addMovieList(popularMoviesCache.asEntity(POPULAR_FILTER))

            val topRatedMoviesCache = movieService.getMovies(TOP_RATED_FILTER)
            movieDao.addMovieList(topRatedMoviesCache.asEntity(TOP_RATED_FILTER))
        }
    }
}
