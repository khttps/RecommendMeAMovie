package com.example.recommendmeamovie.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.paging.MoviePagingSource
import com.example.recommendmeamovie.repository.SearchRepository
import com.example.recommendmeamovie.source.remote.service.MovieApiService
import com.example.recommendmeamovie.util.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class SearchRepositoryImpl
@Inject constructor(private val movieService: MovieApiService) : SearchRepository {

    override fun getSearchResults(query: String) = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 60,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = {
            MoviePagingSource(movieService, query)
        }
    ).flow
}
