package com.example.recommendmeamovie.repository

import androidx.paging.PagingData
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.util.Resource
import kotlinx.coroutines.flow.Flow

interface SearchRepository {


    fun getSearchResults(query: String) : Flow<PagingData<Movie>>

}