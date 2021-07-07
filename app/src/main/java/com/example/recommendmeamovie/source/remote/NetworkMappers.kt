package com.example.recommendmeamovie.source.remote

import com.example.recommendmeamovie.domain.Credit
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.source.local.database.MovieEntity
import com.example.recommendmeamovie.source.remote.dto.Cast
import com.example.recommendmeamovie.source.remote.dto.Crew
import com.example.recommendmeamovie.source.remote.dto.MoviesContainer
import com.example.recommendmeamovie.source.remote.dto.NetworkMovieDetails

fun MoviesContainer.asDomain(): List<Movie> = results.map {
    Movie(
        id = it.id,
        title = it.title,
        poster = it.poster,
        releaseDate = it.releaseDate,
    )
}

fun MoviesContainer.asEntity(movieType: String): List<MovieEntity> = results.map {
    MovieEntity(
        movieId = it.id,
        title = it.title,
        poster = it.poster,
        releaseDate = it.releaseDate,
        movieType = movieType,
    )
}

fun NetworkMovieDetails.asDomain(): MovieDetails {
    return MovieDetails(
        id = id,
        title = title,
        overview = overview,
        runtime = runtime,
        genres = genres?.take(2)?.joinToString(
            separator = ", ",
            transform = { it.name }
        ) ?: "",
        cast = credits.cast?.asCastDomain(),
        crew = credits.crew?.asCrewDomain(),
        poster = poster,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        watchlist = accountStates?.watchlist ?: false,
        favorite = accountStates?.favorite ?: false
    )
}

fun List<Cast>.asCastDomain(): List<Credit> = this.map {
    Credit(
        name = it.name,
        role = it.character,
        picture = it.picture
    )
}

fun List<Crew>.asCrewDomain() = this.map {
    Credit(
        name = it.name,
        role = it.job,
        picture = it.picture
    )
}