package com.example.recommendmeamovie.source.remote

import com.example.recommendmeamovie.domain.Credit
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.utils.EntityMapper
import com.example.recommendmeamovie.utils.Utils
import javax.inject.Inject

class NetworkMovieMapper
@Inject constructor() : EntityMapper<NetworkMovie, Movie> {
    override fun mapToDomain(entity: NetworkMovie): Movie {
        return Movie(
            id = entity.id,
            title = entity.title,
            poster = entity.poster,
            releaseDate = entity.releaseDate
        )
    }

}

class NetworkMovieDetailsMapper
@Inject constructor(private val castMapper: CastMapper, private val crewMapper: CrewMapper)
    : EntityMapper<NetworkMovieDetails, MovieDetails> {

    override fun mapToDomain(entity: NetworkMovieDetails): MovieDetails {
        return MovieDetails(
            entity.id,
            entity.title,
            entity.overview,
            entity.runtime,
            Utils.getGenreString(entity.genres),
            castMapper.mapFromEntityList(entity.credits.cast),
            crewMapper.mapFromEntityList(entity.credits.crew),
            entity.poster,
            entity.releaseDate,
            entity.voteAverage
        )
    }

}

class CastMapper
@Inject constructor() : EntityMapper<Cast, Credit> {
    override fun mapToDomain(entity: Cast): Credit {
        return Credit(
            entity.name,
            entity.character,
            entity.picture
        )
    }
}

class CrewMapper
@Inject constructor() : EntityMapper<Crew, Credit> {
    override fun mapToDomain(entity: Crew): Credit {
        return Credit(
            entity.name,
            entity.job,
            entity.picture
        )
    }

}