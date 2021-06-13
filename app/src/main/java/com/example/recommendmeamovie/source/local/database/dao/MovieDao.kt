package com.example.recommendmeamovie.source.local.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recommendmeamovie.source.local.database.MovieEntity

@Dao
interface MovieDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies : List<MovieEntity>)

    @Insert
    suspend fun addMovie(movie : MovieEntity)

    @Query("SELECT * FROM movies WHERE movie_type = :filter ORDER BY CASE WHEN :ascending = 1 THEN id END ASC, CASE WHEN :ascending = 0 THEN id END DESC")
    fun getMoviesPaged(filter : String, ascending: Boolean) : PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movies WHERE movie_type = :filter ORDER BY id DESC")
    fun getMovies(filter : String) : List<MovieEntity>

    @Query("DELETE FROM movies WHERE movie_type = :filter")
    suspend fun deleteAll(filter: String)

}