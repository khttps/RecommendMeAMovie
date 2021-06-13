package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.repository.interfaces.MovieDetailsRepository
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

    override fun getMovieDetails(id: Long) =  flow {
        emit(Resource.Loading())

        val flow = try {
                Resource.Success(movieService.getMovieDetails(id).asDomain())
        } catch (throwable: Throwable) {
            Resource.Error(throwable = throwable)
        }

        emit(flow)
    }

}