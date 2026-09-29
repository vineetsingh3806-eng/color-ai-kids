package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [
        ArtworkEntity::class,
        UserStatsEntity::class,
        UnlockedRewardEntity::class,
        RecentPageEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artworkDao(): ArtworkDao
    abstract fun rewardDao(): RewardDao
    abstract fun recentPageDao(): RecentPageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `user_stats` (
                        `id` INTEGER NOT NULL,
                        `totalStars` INTEGER NOT NULL,
                        `totalArtworksSaved` INTEGER NOT NULL,
                        `lastActiveDate` INTEGER NOT NULL,
                        PRIMARY KEY(`id`)
                    )
                    """.trimIndent()
                )
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `unlocked_rewards` (
                        `rewardId` TEXT NOT NULL,
                        `unlockedAt` INTEGER NOT NULL,
                        PRIMARY KEY(`rewardId`)
                    )
                    """.trimIndent()
                )
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `recent_pages` (
                        `pageId` TEXT NOT NULL,
                        `title` TEXT NOT NULL,
                        `categoryId` TEXT NOT NULL,
                        `emoji` TEXT NOT NULL,
                        `lastOpenedAt` INTEGER NOT NULL,
                        PRIMARY KEY(`pageId`)
                    )
                    """.trimIndent()
                )
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "colorai_kids_db"
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
