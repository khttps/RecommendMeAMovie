package com.example.recommendmeamovie.repository.impl

import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.repository.MovieDetailsRepository
import com.example.recommendmeamovie.source.remote.asDomain
import com.example.recommendmeamovie.source.remote.service.MovieApiService
import com.example.recommendmeamovie.util.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class MovieDetailsRepositoryImpl @Inject constructor(
    private val movieService: MovieApiService
) : MovieDetailsRepository {

    override fun getMovieDetails(id: Long) =  flow<Resource<MovieDetails>> {

        emit(Resource.Loading())

        try {
            emit(
                Resource.Success(
                    movieService.getMovieDetails(id).asDomain()
                )
            )
        } catch (throwable: Throwable) {
            emit(
                Resource.Error(throwable = throwable)
            )
        }

    }

}