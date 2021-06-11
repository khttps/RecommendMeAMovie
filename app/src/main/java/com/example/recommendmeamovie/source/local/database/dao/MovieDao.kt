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

    @Query("SELECT * FROM movies WHERE movie_type = :filter ORDER BY page ASC")
    fun getMoviesPaged(filter : String) : PagingSource<Int, MovieEntity>

    @Query("DELETE FROM movies WHERE movie_type = :filter")
    suspend fun deleteAll(filter: String)

}