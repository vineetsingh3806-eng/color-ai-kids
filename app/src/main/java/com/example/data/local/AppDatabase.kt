package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

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

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "colorai_kids_db"
                )
                .fallbackToDestructiveMigration(dropAllTables = true)
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
