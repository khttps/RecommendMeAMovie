package com.example.recommendmeamovie.source.remote

import com.example.recommendmeamovie.domain.Credit
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.source.local.MovieEntity
import com.example.recommendmeamovie.source.local.MovieType
import com.example.recommendmeamovie.utils.EntityMapper
import com.example.recommendmeamovie.utils.Utils
import javax.inject.Inject

class NetworkMovieMapper
@Inject constructor() : EntityMapper<NetworkMovie, Movie> {
    override fun map(entity: NetworkMovie): Movie {
        return Movie(
            id = entity.id,
            title = entity.title,
            poster = entity.poster,
            releaseDate = entity.releaseDate
        )
    }

    fun mapList(container: MoviesContainer): List<Movie>? {
        return container.results.map {
            map(it)
        }
    }

}

class NetworkCacheMovieMapper
@Inject constructor() : EntityMapper<NetworkMovie, MovieEntity> {
    private fun mapWithType(entity: NetworkMovie, movieType: MovieType): MovieEntity {
        return MovieEntity(
            id = entity.id,
            title = entity.title,
            poster = entity.poster,
            releaseDate = entity.releaseDate,
            movieType = movieType
        )
    }

    fun mapList(container: MoviesContainer, movieType: MovieType): List<MovieEntity>? {
        return container.results.map {
            mapWithType(it, movieType)
        }
    }

}

class NetworkMovieDetailsMapper
@Inject constructor(private val castMapper: CastMapper, private val crewMapper: CrewMapper)
    : EntityMapper<NetworkMovieDetails, MovieDetails> {

    override fun map(entity: NetworkMovieDetails): MovieDetails {
        return MovieDetails(
            entity.id,
            entity.title,
            entity.overview,
            entity.runtime,
            Utils.getGenreString(entity.genres),
            castMapper.mapList(entity.credits.cast),
            crewMapper.mapList(entity.credits.crew),
            entity.poster,
            entity.releaseDate,
            entity.voteAverage
        )
    }

}

class CastMapper
@Inject constructor() : EntityMapper<Cast, Credit> {
    override fun map(entity: Cast): Credit {
        return Credit(
            entity.name,
            entity.character,
            entity.picture
        )
    }
}

class CrewMapper
@Inject constructor() : EntityMapper<Crew, Credit> {
    override fun map(entity: Crew): Credit {
        return Credit(
            entity.name,
            entity.job,
            entity.picture
        )
    }

}