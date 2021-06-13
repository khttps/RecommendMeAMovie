package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.domain.Credit
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.repository.interfaces.MovieDetailsRepository
import com.example.recommendmeamovie.util.Resource
import kotlinx.coroutines.flow.flow

class FakeMovieDetailsRepository(): MovieDetailsRepository {

    private val credit = listOf(
        Credit("","", "")
    )

    private val movies = listOf(
        MovieDetails(1L, "Taxi Driver", "", 0, "", credit, credit, "", "1977", 0.0),
        MovieDetails(2L, "Star Wars", "", 0, "", credit, credit, "", "", 0.0),
        MovieDetails(3L, "The Fellowship of the Ring", "", 0, "", credit, credit, "2001", "", 0.0)
    )

    override fun getMovieDetails(id: Long) = flow<Resource<MovieDetails>> {
        emit(Resource.Loading())

        val details = movies.find { it.id == id }

        if (details == null)
            emit(Resource.Error(Throwable()))
        else
            emit(Resource.Success(details))
    }
}