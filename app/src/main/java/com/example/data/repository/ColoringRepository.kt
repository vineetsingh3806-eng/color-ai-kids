package com.example.data.repository

import com.example.data.local.RecentPageDao
import com.example.data.local.RecentPageEntity
import com.example.data.model.Category
import com.example.data.model.ColoringPage
import com.example.domain.coloring.ColoringPageTemplates
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ColoringRepository(
    private val recentPageDao: RecentPageDao
) {
    fun getAllPages(): List<ColoringPage> = ColoringPageTemplates.allPreloadedPages

    fun getPagesByCategory(category: Category): List<ColoringPage> =
        if (category == Category.ALL) {
            ColoringPageTemplates.allPreloadedPages
        } else {
            ColoringPageTemplates.getPagesForCategory(category)
        }

    fun getPageById(id: String): ColoringPage? = ColoringPageTemplates.getPageById(id)

    fun getDailyFeaturedPage(): ColoringPage = ColoringPageTemplates.getDailyFeaturedPage()

    fun getRecentPagesFlow(): Flow<List<ColoringPage>> {
        return recentPageDao.getRecentPagesFlow().map { list ->
            list.map { entity ->
                ColoringPageTemplates.getPageById(entity.pageId) ?: ColoringPage(
                    id = entity.pageId,
                    title = entity.title,
                    category = Category.fromId(entity.categoryId),
                    emoji = entity.emoji,
                    description = "Custom coloring page"
                )
            }
        }
    }

    suspend fun recordRecentPage(page: ColoringPage) {
        recentPageDao.recordRecentPage(
            RecentPageEntity(
                pageId = page.id,
                title = page.title,
                categoryId = page.category.id,
                emoji = page.emoji,
                lastOpenedAt = System.currentTimeMillis()
            )
        )
    }

    suspend fun clearRecentPages() {
        recentPageDao.clearRecentPages()
    }
}
