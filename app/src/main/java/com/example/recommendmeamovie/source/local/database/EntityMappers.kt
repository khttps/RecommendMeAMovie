package com.example.recommendmeamovie.source.local.database

import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.domain.Question

fun MovieEntity.asDomain() = Movie(
    id = id,
    title = title,
    poster = poster,
    releaseDate = releaseDate
)


fun List<QuestionEntity>.asQuestionDomain(): List<Question> {

    return map {
        Question(
            questionText = it.question,
            choices = it.choices
        )
    }

}