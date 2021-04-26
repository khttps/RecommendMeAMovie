package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.source.local.MovieDao
import com.example.recommendmeamovie.source.local.asDomain
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.source.remote.asEntity
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
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

    suspend fun getPopularMovies(): List<Movie>? {
        return movieDao.getMovies(POPULAR_FILTER).asDomain()
    }

    suspend fun getTopRatedMovies(): List<Movie>? {
        return movieDao.getMovies(TOP_RATED_FILTER).asDomain()
    }

    suspend fun refreshCacheData() {
        withContext(Dispatchers.IO) {
            val popularMovies = movieService.getMovies(POPULAR_FILTER, BuildConfig.API_KEY)
            movieDao.addMovieList(popularMovies.asEntity(POPULAR_FILTER))

            val topRatedMovies = movieService.getMovies(TOP_RATED_FILTER, BuildConfig.API_KEY)
            movieDao.addMovieList(topRatedMovies.asEntity(TOP_RATED_FILTER))
        }
    }

}