package com.example.data.repository

import com.example.data.local.RewardDao
import com.example.data.local.UnlockedRewardEntity
import com.example.data.local.UserStatsEntity
import com.example.data.model.RewardItem
import com.example.data.model.RewardType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class RewardsRepository(
    private val rewardDao: RewardDao
) {
    val allCatalogRewards = listOf(
        RewardItem(
            id = "pack_sparkle",
            title = "Rainbow Sparkle Pack",
            description = "Unlocks magical glitter gold & rainbow sparkle crayons in Coloring Studio!",
            iconEmoji = "✨",
            starsRequired = 10,
            isUnlocked = false,
            type = RewardType.CRAYON_PACK
        ),
        RewardItem(
            id = "bg_sunset",
            title = "Pastel Sunset Frames",
            description = "Unlocks warm sunset canvas frames and borders for your artwork!",
            iconEmoji = "🌅",
            starsRequired = 25,
            isUnlocked = false,
            type = RewardType.BACKGROUND
        ),
        RewardItem(
            id = "char_pegasus",
            title = "Baby Pegasus Character",
            description = "Unlocks the secret Flying Pegasus magical coloring page!",
            iconEmoji = "🦄",
            starsRequired = 50,
            isUnlocked = false,
            type = RewardType.CHARACTER
        ),
        RewardItem(
            id = "pack_master",
            title = "Master Colorist Crown Pack",
            description = "Special royal badge and golden star artist certificate!",
            iconEmoji = "👑",
            starsRequired = 100,
            isUnlocked = false,
            type = RewardType.SPECIAL_PACK
        )
    )

    val userStatsFlow: Flow<UserStatsEntity> = rewardDao.getUserStatsFlow().map { it ?: UserStatsEntity() }

    val rewardsWithStatusFlow: Flow<List<RewardItem>> = combine(
        userStatsFlow,
        rewardDao.getUnlockedRewardIdsFlow()
    ) { stats, unlockedIds ->
        val unlockedSet = unlockedIds.toSet()
        allCatalogRewards.map { item ->
            val unlocked = unlockedSet.contains(item.id) || stats.totalStars >= item.starsRequired
            item.copy(isUnlocked = unlocked)
        }
    }

    suspend fun addStars(stars: Int) {
        val current = rewardDao.getUserStats()
        if (current == null) {
            rewardDao.saveUserStats(UserStatsEntity(totalStars = 20 + stars, totalArtworksSaved = 1))
        } else {
            rewardDao.addStars(stars)
        }
    }

    suspend fun unlockReward(rewardId: String) {
        rewardDao.unlockReward(UnlockedRewardEntity(rewardId))
    }

    suspend fun resetAllStats() {
        rewardDao.resetStats()
        rewardDao.resetRewards()
        rewardDao.saveUserStats(UserStatsEntity(totalStars = 20, totalArtworksSaved = 0))
    }
}
