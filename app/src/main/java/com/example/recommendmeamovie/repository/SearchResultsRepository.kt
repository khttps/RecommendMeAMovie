package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.source.remote.asDomain
import com.example.recommendmeamovie.util.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ViewModelScoped
class SearchResultsRepository
@Inject constructor(private val movieService : MovieApiService) {

    fun getSearchResults(query: String) = flow<Resource<List<Movie>>> {

        emit(Resource.Loading(null))

        try {
            emit(
                Resource.Success(
                    movieService.getSearchResults(query).asDomain()
                )
            )
        } catch (throwable : Throwable) {
            emit(Resource.Error(throwable, null))
        }
    }.flowOn(Dispatchers.IO)
}