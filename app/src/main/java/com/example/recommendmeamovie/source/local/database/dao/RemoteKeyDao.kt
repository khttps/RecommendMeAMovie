package com.example.recommendmeamovie.source.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recommendmeamovie.source.local.database.RemoteKey

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKey)

    @Query("SELECT * FROM remote_keys WHERE filter = :filter")
    suspend fun remoteKeyByQuery(filter: String): RemoteKey

    @Query("DELETE FROM remote_keys WHERE filter = :query")
    suspend fun deleteByQuery(query: String)

}