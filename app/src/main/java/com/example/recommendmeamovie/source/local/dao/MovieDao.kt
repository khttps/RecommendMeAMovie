package com.example.recommendmeamovie.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recommendmeamovie.source.local.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieList(movies : List<MovieEntity>)

    @Insert
    suspend fun addMovie(movie : MovieEntity)

    @Query("SELECT * FROM movies WHERE movie_type = :filter")
    fun getMovies(filter : String) : Flow<List<MovieEntity>>

    @Query("DELETE FROM movies WHERE movie_type = :filter")
    fun deleteMovies(filter: String)

}