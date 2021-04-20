package com.example.recommendmeamovie.source.local

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun toMovieType(value: Int) = enumValues<MovieType>()[value]

    @TypeConverter
    fun fromMovieType(value: MovieType) = value.ordinal
}