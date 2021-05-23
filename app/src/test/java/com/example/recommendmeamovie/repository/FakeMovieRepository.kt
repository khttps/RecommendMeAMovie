package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.util.Resource
import kotlinx.coroutines.flow.flow

class FakeMovieRepository(private val movieDataSource : List<Movie>) : MovieRepository {

    private var isConnected = false

    fun setNetworkConnected(value: Boolean) {
        isConnected = value
    }

    override fun getPopularMovies() = getMovies()
    override fun getTopRatedMovies() = getMovies()

    private fun getMovies() = flow<Resource<List<Movie>>> {
        emit(Resource.Loading())
        if (!isConnected) {
            emit(Resource.Error(Throwable(), null))
        } else {
            emit(Resource.Success(movieDataSource))
        }
    }
}