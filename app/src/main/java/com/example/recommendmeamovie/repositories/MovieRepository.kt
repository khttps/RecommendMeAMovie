package com.example.recommendmeamovie.repositories

import com.example.recommendmeamovie.BuildConfig
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.source.local.MovieDao
import com.example.recommendmeamovie.source.local.MovieEntityMapper
import com.example.recommendmeamovie.source.local.MovieType
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.source.remote.NetworkCacheMovieMapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MovieRepository
@Inject constructor(
    private val movieService : MovieApiService,
    private val movieDao: MovieDao,
    private val networkCacheMovieMapper: NetworkCacheMovieMapper,
    private val movieEntityMapper: MovieEntityMapper
    ) {

    companion object {
        private const val POPULAR_FILTER = "popular"
        private const val TOP_RATED_FILTER = "top_rated"
    }

    suspend fun getPopularMovies(): List<Movie>? {

        val popularMovies = movieService.getMovies(POPULAR_FILTER, BuildConfig.API_KEY)
        networkCacheMovieMapper.mapList(popularMovies, MovieType.POPULAR)?.let {
            movieDao.addMovieList(it)
        }

        return movieEntityMapper.mapList(
            movieDao.getMovies(MovieType.POPULAR)
        )
    }

    suspend fun getTopRatedMovies(): List<Movie>? {

        val topRatedMovies = movieService.getMovies(TOP_RATED_FILTER, BuildConfig.API_KEY)
        networkCacheMovieMapper.mapList(topRatedMovies, MovieType.TOP_RATED)?.let {
            movieDao.addMovieList(it)
        }

        return movieEntityMapper.mapList(
            movieDao.getMovies(MovieType.TOP_RATED)
        )
    }



}