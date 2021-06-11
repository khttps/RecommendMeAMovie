package com.example.recommendmeamovie.repository.impl

import androidx.paging.*
import androidx.room.withTransaction
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.paging.MovieRemoteMediator
import com.example.recommendmeamovie.repository.MovieRepository
import com.example.recommendmeamovie.source.local.database.MovieDatabase
import com.example.recommendmeamovie.source.local.database.asDomain
import com.example.recommendmeamovie.source.remote.service.MovieApiService
import com.example.recommendmeamovie.source.remote.asEntity
import com.example.recommendmeamovie.util.networkBoundResource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
@ViewModelScoped
class MovieRepositoryImpl
@Inject constructor(
    private val movieService: MovieApiService,
    private val movieDatabase: MovieDatabase
) : MovieRepository {

    private val movieDao = movieDatabase.movieDao

    companion object {
        private const val POPULAR_FILTER = "popular"
        private const val TOP_RATED_FILTER = "top_rated"
    }

    override fun getPopularMovies() = getCachedMovies(POPULAR_FILTER)
    override fun getTopRatedMovies() = getCachedMovies(TOP_RATED_FILTER)

    private fun getCachedMovies(filter : String) = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 60,
            enablePlaceholders = false,
        ),
        remoteMediator = MovieRemoteMediator(filter, movieService, movieDatabase)
    ) {
        movieDao.getMoviesPaged(filter)
    }.flow.map { pagingData ->
        pagingData.map { entity ->
            entity.asDomain()
        }
    }
}
