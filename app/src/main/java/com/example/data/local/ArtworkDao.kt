package com.example.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtworkDao {
    @Query("SELECT * FROM saved_artworks ORDER BY createdAt DESC")
    fun getAllArtworks(): Flow<List<ArtworkEntity>>

    @Query("SELECT * FROM saved_artworks WHERE id = :id LIMIT 1")
    suspend fun getArtworkById(id: String): ArtworkEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtwork(artwork: ArtworkEntity)

    @Delete
    suspend fun deleteArtwork(artwork: ArtworkEntity)

    @Query("DELETE FROM saved_artworks WHERE id = :id")
    suspend fun deleteArtworkById(id: String)

    @Query("DELETE FROM saved_artworks")
    suspend fun deleteAllArtworks()
}
