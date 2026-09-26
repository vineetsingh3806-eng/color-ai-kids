package com.example.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

object ImageStorageManager {

    private const val ARTWORK_DIR = "artworks"

    /**
     * Saves a coloring bitmap to private app storage.
     */
    fun saveArtworkBitmap(context: Context, artworkId: String, bitmap: Bitmap): String {
        val dir = File(context.filesDir, ARTWORK_DIR)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        val file = File(dir, "artwork_$artworkId.png")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        return file.absolutePath
    }

    /**
     * Loads a saved bitmap from file path.
     */
    fun loadArtworkBitmap(filePath: String): Bitmap? {
        val file = File(filePath)
        if (!file.exists()) return null
        return BitmapFactory.decodeFile(file.absolutePath)
    }

    /**
     * Deletes a local artwork file.
     */
    fun deleteArtworkFile(filePath: String): Boolean {
        val file = File(filePath)
        return if (file.exists()) file.delete() else true
    }

    /**
     * Deletes all local saved artwork files (Parent Zone feature).
     */
    fun clearAllArtworks(context: Context) {
        val dir = File(context.filesDir, ARTWORK_DIR)
        if (dir.exists()) {
            dir.deleteRecursively()
        }
    }

    /**
     * Creates a share intent to share/export child's completed artwork.
     */
    fun createShareIntent(context: Context, filePath: String, title: String): Intent? {
        val file = File(filePath)
        if (!file.exists()) return null

        val uri: Uri = try {
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )
        } catch (e: Exception) {
            Uri.fromFile(file)
        }

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "image/png"
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(Intent.EXTRA_TEXT, "Look at my colorful $title! Made with ColorAI Kids 🎨")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        return Intent.createChooser(shareIntent, "Share your artwork")
    }
}
