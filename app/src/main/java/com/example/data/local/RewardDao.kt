package com.example.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "user_stats")
data class UserStatsEntity(
    @PrimaryKey val id: Int = 1,
    val totalStars: Int = 20, // Start with a welcoming initial gift of stars for a child
    val totalArtworksSaved: Int = 0,
    val lastActiveDate: Long = System.currentTimeMillis()
)

@Entity(tableName = "unlocked_rewards")
data class UnlockedRewardEntity(
    @PrimaryKey val rewardId: String,
    val unlockedAt: Long = System.currentTimeMillis()
)

@Dao
interface RewardDao {
    @Query("SELECT * FROM user_stats WHERE id = 1")
    fun getUserStatsFlow(): Flow<UserStatsEntity?>

    @Query("SELECT * FROM user_stats WHERE id = 1")
    suspend fun getUserStats(): UserStatsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserStats(stats: UserStatsEntity)

    @Query("UPDATE user_stats SET totalStars = totalStars + :stars, totalArtworksSaved = totalArtworksSaved + 1 WHERE id = 1")
    suspend fun addStars(stars: Int)

    @Query("SELECT rewardId FROM unlocked_rewards")
    fun getUnlockedRewardIdsFlow(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun unlockReward(reward: UnlockedRewardEntity)

    @Query("DELETE FROM user_stats")
    suspend fun resetStats()

    @Query("DELETE FROM unlocked_rewards")
    suspend fun resetRewards()
}
