package com.example.data.repository

import android.content.Context
import android.graphics.Bitmap
import com.example.data.local.ArtworkDao
import com.example.data.local.ArtworkEntity
import com.example.data.model.SavedArtwork
import com.example.utils.ImageStorageManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.UUID

class ArtworkRepository(
    private val context: Context,
    private val artworkDao: ArtworkDao
) {
    val allArtworksFlow: Flow<List<SavedArtwork>> = artworkDao.getAllArtworks().map { list ->
        list.map { entity ->
            SavedArtwork(
                id = entity.id,
                title = entity.title,
                categoryId = entity.categoryId,
                imageFilePath = entity.imageFilePath,
                templateId = entity.templateId,
                createdAt = entity.createdAt,
                isCompleted = entity.isCompleted,
                starsAwarded = entity.starsAwarded
            )
        }
    }

    suspend fun saveArtwork(
        title: String,
        categoryId: String,
        templateId: String,
        bitmap: Bitmap,
        isCompleted: Boolean = true,
        starsAwarded: Int = 0,
        existingId: String? = null
    ): SavedArtwork = withContext(Dispatchers.IO) {
        val artworkId = existingId ?: UUID.randomUUID().toString()
        val filePath = ImageStorageManager.saveArtworkBitmap(context, artworkId, bitmap)

        val entity = ArtworkEntity(
            id = artworkId,
            title = title,
            categoryId = categoryId,
            imageFilePath = filePath,
            templateId = templateId,
            createdAt = System.currentTimeMillis(),
            isCompleted = isCompleted,
            starsAwarded = starsAwarded
        )
        artworkDao.insertArtwork(entity)

        SavedArtwork(
            id = artworkId,
            title = title,
            categoryId = categoryId,
            imageFilePath = filePath,
            templateId = templateId,
            createdAt = entity.createdAt,
            isCompleted = isCompleted,
            starsAwarded = starsAwarded
        )
    }

    suspend fun getArtworkById(id: String): SavedArtwork? = withContext(Dispatchers.IO) {
        artworkDao.getArtworkById(id)?.let { entity ->
            SavedArtwork(
                id = entity.id,
                title = entity.title,
                categoryId = entity.categoryId,
                imageFilePath = entity.imageFilePath,
                templateId = entity.templateId,
                createdAt = entity.createdAt,
                isCompleted = entity.isCompleted,
                starsAwarded = entity.starsAwarded
            )
        }
    }

    suspend fun loadArtworkBitmap(filePath: String): Bitmap? = withContext(Dispatchers.IO) {
        ImageStorageManager.loadArtworkBitmap(filePath)
    }

    suspend fun deleteArtwork(artwork: SavedArtwork) = withContext(Dispatchers.IO) {
        ImageStorageManager.deleteArtworkFile(artwork.imageFilePath)
        artworkDao.deleteArtworkById(artwork.id)
    }

    suspend fun deleteAllArtworks() = withContext(Dispatchers.IO) {
        ImageStorageManager.clearAllArtworks(context)
        artworkDao.deleteAllArtworks()
    }
}
