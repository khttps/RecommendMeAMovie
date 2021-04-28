package com.example.recommendmeamovie.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieList(movies : List<MovieEntity>)

    @Insert
    suspend fun addMovie(movie : MovieEntity)

    @Query("SELECT * FROM movies WHERE movie_type = :filter")
    fun getMovies(filter : String) : LiveData<List<MovieEntity>>


}