package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(): Flow<Resource<List<Movie>>>
    fun getTopRatedMovies(): Flow<Resource<List<Movie>>>

}