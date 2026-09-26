package com.example.domain.coloring

import android.graphics.Bitmap
import androidx.collection.LruCache
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

object ThumbnailCache {
    private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
    // Use up to 1/4th of available memory (at least 64MB) to easily hold all 220+ thumbnails without eviction
    private val cacheSize = (maxMemory / 4).coerceAtLeast(1024 * 64)

    private val memoryCache = object : LruCache<String, Bitmap>(cacheSize) {
        override fun sizeOf(key: String, value: Bitmap): Int {
            return value.byteCount / 1024
        }
    }

    private val imageBitmapCache = object : LruCache<String, ImageBitmap>(cacheSize) {
        override fun sizeOf(key: String, value: ImageBitmap): Int {
            return (value.width * value.height * 4) / 1024
        }
    }

    fun getCachedThumbnail(templateId: String, size: Int = 256): Bitmap? {
        val cacheKey = "${templateId}_$size"
        synchronized(memoryCache) {
            val cached = memoryCache[cacheKey]
            if (cached != null && !cached.isRecycled) {
                return cached
            }
        }
        return null
    }

    fun getCachedImageBitmap(templateId: String, size: Int = 256): ImageBitmap? {
        val cacheKey = "${templateId}_$size"
        synchronized(imageBitmapCache) {
            val cached = imageBitmapCache[cacheKey]
            if (cached != null) {
                return cached
            }
        }
        val bmp = getCachedThumbnail(templateId, size) ?: return null
        val imgBmp = bmp.asImageBitmap()
        synchronized(imageBitmapCache) {
            imageBitmapCache.put(cacheKey, imgBmp)
        }
        return imgBmp
    }

    fun getThumbnail(templateId: String, size: Int = 256): Bitmap {
        val cacheKey = "${templateId}_$size"
        synchronized(memoryCache) {
            val cached = memoryCache[cacheKey]
            if (cached != null && !cached.isRecycled) {
                return cached
            }
        }

        val bitmap = ColoringPageTemplates.createTemplateBitmap(templateId, size)
        val imgBmp = bitmap.asImageBitmap()
        synchronized(memoryCache) {
            memoryCache.put(cacheKey, bitmap)
        }
        synchronized(imageBitmapCache) {
            imageBitmapCache.put(cacheKey, imgBmp)
        }
        return bitmap
    }

    fun getImageBitmap(templateId: String, size: Int = 256): ImageBitmap {
        val cacheKey = "${templateId}_$size"
        synchronized(imageBitmapCache) {
            val cached = imageBitmapCache[cacheKey]
            if (cached != null) {
                return cached
            }
        }
        val bitmap = getThumbnail(templateId, size)
        val imgBmp = bitmap.asImageBitmap()
        synchronized(imageBitmapCache) {
            imageBitmapCache.put(cacheKey, imgBmp)
        }
        return imgBmp
    }

    fun preloadThumbnails(templateIds: List<String>, size: Int = 256) {
        for (templateId in templateIds) {
            val cacheKey = "${templateId}_$size"
            val alreadyCached = synchronized(memoryCache) { memoryCache[cacheKey] != null }
            if (!alreadyCached) {
                try {
                    val bitmap = ColoringPageTemplates.createTemplateBitmap(templateId, size)
                    val imgBmp = bitmap.asImageBitmap()
                    synchronized(memoryCache) {
                        memoryCache.put(cacheKey, bitmap)
                    }
                    synchronized(imageBitmapCache) {
                        imageBitmapCache.put(cacheKey, imgBmp)
                    }
                } catch (e: Throwable) {
                    // Ignore transient errors during preload
                }
            }
        }
    }

    fun clear() {
        synchronized(memoryCache) {
            memoryCache.evictAll()
        }
        synchronized(imageBitmapCache) {
            imageBitmapCache.evictAll()
        }
    }
}

