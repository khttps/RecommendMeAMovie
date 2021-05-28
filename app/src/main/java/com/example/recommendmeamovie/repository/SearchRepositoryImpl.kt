package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.source.remote.asDomain
import com.example.recommendmeamovie.util.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class SearchRepositoryImpl
@Inject constructor(private val movieService: MovieApiService) : SearchRepository {

    override fun getSearchResults(query: String) = flow<Resource<List<Movie>>> {
        emit(Resource.Loading(null))

        try {
            emit(
                Resource.Success(
                    movieService.getSearchResults(query).asDomain()
                )
            )
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable, null))
        }
    }
}