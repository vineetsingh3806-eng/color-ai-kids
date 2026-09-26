package com.example.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "recent_pages")
data class RecentPageEntity(
    @PrimaryKey val pageId: String,
    val title: String,
    val categoryId: String,
    val emoji: String,
    val lastOpenedAt: Long = System.currentTimeMillis()
)

@Dao
interface RecentPageDao {
    @Query("SELECT * FROM recent_pages ORDER BY lastOpenedAt DESC LIMIT 6")
    fun getRecentPagesFlow(): Flow<List<RecentPageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun recordRecentPage(page: RecentPageEntity)

    @Query("DELETE FROM recent_pages")
    suspend fun clearRecentPages()
}
