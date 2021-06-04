package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.util.Resource
import kotlinx.coroutines.flow.Flow

interface SearchRepository {


    fun getSearchResults(query: String) : Flow<Resource<List<Movie>>>

}