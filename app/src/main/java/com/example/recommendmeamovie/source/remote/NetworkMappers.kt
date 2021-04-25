package com.example.recommendmeamovie.source.remote

import com.example.recommendmeamovie.domain.Credit
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.source.local.MovieEntity
import com.example.recommendmeamovie.util.Utils

fun MoviesContainer.asDomain() : List<Movie> {
    return results.map {
        Movie(
            id = it.id,
            title = it.title,
            poster = it.poster,
            releaseDate = it.releaseDate
        )
    }

}

fun MoviesContainer.asEntity(movieType: String) : List<MovieEntity> {
    return results.map {
        MovieEntity(
            id = it.id,
            title = it.title,
            poster = it.poster,
            releaseDate = it.releaseDate,
            movieType = movieType
        )
    }

}

fun NetworkMovieDetails.asDomain() : MovieDetails {
    return MovieDetails(
        id = id,
        title = title,
        overview = overview,
        runtime = runtime,
        genres = Utils.getGenreString(genres),
        cast = credits.cast?.asCastDomain(),
        crew = credits.crew?.asCrewDomain(),
        poster = poster,
        releaseDate = releaseDate,
        voteAverage = voteAverage
    )
}

fun List<Cast>.asCastDomain() : List<Credit> {
    return this.map {
        Credit(
            name = it.name,
            role = it.character,
            picture = it.picture
        )
    }
}

fun List<Crew>.asCrewDomain() : List<Credit> {
    return this.map {
        Credit(
            name = it.name,
            role = it.job,
            picture = it.picture
        )
    }
}