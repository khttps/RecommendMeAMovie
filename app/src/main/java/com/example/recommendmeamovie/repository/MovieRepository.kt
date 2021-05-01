package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.source.local.MovieDao
import com.example.recommendmeamovie.source.local.asDomain
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.source.remote.asEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
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

    val popularMovies = movieDao.getMovies(POPULAR_FILTER).flowOn(Dispatchers.IO).map {
        it.asDomain()
    }

    val topRatedMovies = movieDao.getMovies(TOP_RATED_FILTER).flowOn(Dispatchers.IO).map {
        it.asDomain()
    }

    suspend fun refreshCacheData() {
        withContext(Dispatchers.IO) {
            val popularMoviesCache = movieService.getMovies(POPULAR_FILTER, BuildConfig.API_KEY)
            movieDao.addMovieList(popularMoviesCache.asEntity(POPULAR_FILTER))

            val topRatedMoviesCache = movieService.getMovies(TOP_RATED_FILTER, BuildConfig.API_KEY)
            movieDao.addMovieList(topRatedMoviesCache.asEntity(TOP_RATED_FILTER))
        }
    }
}
