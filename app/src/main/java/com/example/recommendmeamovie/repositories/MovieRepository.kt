package com.example.recommendmeamovie.repositories

import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.source.remote.asDomainModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MovieRepository @Inject constructor(private val remoteDataSource : MovieApiService) {

    companion object {
        private const val POPULAR_FILTER = "popular"
        private const val TOP_RATED_FILTER = "top_rated"
    }

    suspend fun getPopularMovies(): List<Movie> {
        return remoteDataSource.getMovies(POPULAR_FILTER, BuildConfig.API_KEY).asDomainModel()
    }

    suspend fun getTopRatedMovies(): List<Movie> {
        return remoteDataSource.getMovies(TOP_RATED_FILTER, BuildConfig.API_KEY).asDomainModel()
    }

}