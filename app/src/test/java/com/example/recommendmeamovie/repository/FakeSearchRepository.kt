package com.example.recommendmeamovie.repository

import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.util.Resource
import kotlinx.coroutines.flow.flow

class FakeSearchRepository() : SearchRepository {

    private val movies = listOf(
        Movie(0, "Zack Snyder's Justice League", "", "2021-3-18"),
        Movie(1, "Parasite", "", "2019-5-30"),
        Movie(2, "Evangelion 3.0 + 1.0 Thrice Upon A Time", "", "2021-3-8")
    )

    override fun getSearchResults(query: String) = flow<Resource<List<Movie>>> {
        emit(Resource.Loading())

        val details = movies.filter { it.title.contains(query) }

        if (details.isNotEmpty())
            emit(Resource.Success(details))
        else
            emit(Resource.Error(Throwable(message = "No results matched your search query")))
    }
}