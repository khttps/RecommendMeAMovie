package com.example.recommendmeamovie.repository

import androidx.paging.*
import com.example.recommendmeamovie.paging.MoviePagingSource
import com.example.recommendmeamovie.paging.MovieRemoteMediator
import com.example.recommendmeamovie.repository.interfaces.MovieRepository
import com.example.recommendmeamovie.source.local.database.MovieDatabase
import com.example.recommendmeamovie.source.local.database.asDomain
import com.example.recommendmeamovie.source.remote.service.MovieApiService
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
@ViewModelScoped
class MovieRepositoryImpl
@Inject constructor(
    private val service: MovieApiService,
    private val database: MovieDatabase
) : MovieRepository {
    private val movieDao = database.movieDao

    companion object {
        private const val POPULAR_FILTER = "popular"
        private const val TOP_RATED_FILTER = "top_rated"
        private const val WATCHLIST_FILTER = "watchlist"
        private const val FAVORITES_FILTER = "favorite"
    }

    override val popularMoviesPaged = gePagedMovies(POPULAR_FILTER)
    override val topRatedMoviesPaged = gePagedMovies(TOP_RATED_FILTER)

    private fun gePagedMovies(filter: String) = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 60,
            enablePlaceholders = false,
        ),
        remoteMediator = MovieRemoteMediator(filter, service, database),
        pagingSourceFactory = {
            movieDao.getMoviesPaged(filter = filter, ascending = true)
        }
    ).flow.map {
        it.map { entity ->
            entity.asDomain()
        }
    }

    override fun getPagedSearchResults(query: String) = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 60,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = {
            MoviePagingSource(service, query)
        }
    ).flow
}
