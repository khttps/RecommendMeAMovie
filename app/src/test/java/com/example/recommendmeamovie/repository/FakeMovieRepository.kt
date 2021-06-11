package com.example.recommendmeamovie.repository

import androidx.paging.PagingData
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMovieRepository() : MovieRepository {

    private val movies = listOf(
    Movie(0, "Zack Snyder's Justice League", "", "2021-3-18"),
    Movie(1, "Parasite", "", "2019-5-30"),
    Movie(2, "Evangelion 3.0 + 1.0 Thrice Upon A Time", "", "2021-3-8")
    )

    private var isConnected = false

    fun setNetworkConnected(value: Boolean) {
        isConnected = value
    }

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        TODO("Needs to be refactored into testing PagingData")
    }
    override fun getTopRatedMovies(): Flow<PagingData<Movie>> {
        TODO("Needs to be refactored into testing PagingData")
    }

    private fun getMovies() = flow<Resource<List<Movie>>> {
        emit(Resource.Loading())
        if (!isConnected) {
            emit(Resource.Error(Throwable(), null))
        } else {
            emit(Resource.Success(movies))
        }
    }
}