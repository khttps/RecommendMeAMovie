package com.example.recommendmeamovie.source.remote

import com.example.recommendmeamovie.domain.Credit
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.source.local.MovieEntity
import com.example.recommendmeamovie.utils.Utils

fun MoviesContainer.asDomain() : List<Movie> {
    return results.map {
        Movie(it.id, it.title, it.poster, it.releaseDate)
    }

}

fun MoviesContainer.asEntity(movieType: String) : List<MovieEntity> {
    return results.map {
        MovieEntity(
            it.id,
            it.title,
            it.poster,
            it.releaseDate,
            movieType
        )
    }

}

fun NetworkMovieDetails.asDomain() : MovieDetails {
    return MovieDetails(
        id,
        title,
        overview,
        runtime,
        Utils.getGenreString(genres),
        credits.cast?.asCastDomain(),
        credits.crew?.asCrewDomain(),
        poster,
        releaseDate,
        voteAverage
    )
}

fun List<Cast>.asCastDomain() : List<Credit> {
    return this.map {
        Credit(it.name, it.character, it.picture)
    }
}

fun List<Crew>.asCrewDomain() : List<Credit> {
    return this.map {
        Credit(it.name, it.job, it.picture)
    }
}