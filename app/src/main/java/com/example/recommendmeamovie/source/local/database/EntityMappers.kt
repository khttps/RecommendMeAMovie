package com.example.recommendmeamovie.source.local.database

import com.example.recommendmeamovie.domain.Movie

fun MovieEntity.asDomain() = Movie(
    id = movieId,
    title = title,
    poster = poster,
    releaseDate = releaseDate
)

fun List<MovieEntity>.asDomain() = map {
    it.asDomain()
}