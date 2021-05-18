package com.example.recommendmeamovie.source.local

import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.domain.Question

fun List<MovieEntity>.asMovieDomain() : List<Movie> {

    return map {
        Movie(
            id = it.id,
            title = it.title,
            poster = it.poster,
            releaseDate = it.releaseDate
        )
    }

}

fun List<QuestionEntity>.asQuestionDomain() : List<Question> {

    return map {
        Question( questionText = it.question,
            choices = it.choices)
    }

}