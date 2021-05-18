package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.source.remote.MovieApiService
import com.example.recommendmeamovie.source.remote.asDomain
import com.example.recommendmeamovie.util.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class MovieDetailsRepository
@Inject constructor(
    private val movieService: MovieApiService
) {

    fun getMovieDetails(id: Long) =  flow<Resource<MovieDetails>> {
        emit(Resource.Loading(null))

        try {
            emit(
                Resource.Success(
                    movieService.getMovieDetails(id).asDomain()
                )
            )
        } catch (throwable: Throwable) {
            emit(
                Resource.Error(throwable, null)
            )
        }

    }.flowOn(Dispatchers.IO)

}