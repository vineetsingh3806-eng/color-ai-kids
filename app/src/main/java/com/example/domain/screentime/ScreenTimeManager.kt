package com.example.domain.screentime

import android.content.Context
import android.content.SharedPreferences
import com.example.data.model.ParentSettings
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ScreenTimeManager(
    context: Context,
    private val scope: CoroutineScope
) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    private val _settings = MutableStateFlow(loadSettings())
    val settings: StateFlow<ParentSettings> = _settings.asStateFlow()

    private val _usageSecondsToday = MutableStateFlow(loadUsageSeconds())
    val usageSecondsToday: StateFlow<Long> = _usageSecondsToday.asStateFlow()

    private val _isLimitReached = MutableStateFlow(false)
    val isLimitReached: StateFlow<Boolean> = _isLimitReached.asStateFlow()

    private var tickerJob: Job? = null
    private var isForeground = false

    companion object {
        private const val PREFS_NAME = "parent_screen_time_prefs"
        private const val KEY_SOUND_ENABLED = "sound_enabled"
        private const val KEY_LIMIT_MINUTES = "screen_time_limit_minutes"
        private const val KEY_USAGE_SECONDS = "usage_seconds_today"
        private const val KEY_LAST_DATE = "last_active_date"
        private const val KEY_LIMIT_DISMISSED = "limit_dismissed_today"
    }

    init {
        checkDailyReset()
        evaluateLimit()
    }

    private fun loadSettings(): ParentSettings {
        val sound = prefs.getBoolean(KEY_SOUND_ENABLED, true)
        val limit = prefs.getInt(KEY_LIMIT_MINUTES, 0)
        return ParentSettings(
            soundEnabled = sound,
            screenTimeLimitMinutes = limit
        )
    }

    private fun loadUsageSeconds(): Long {
        return prefs.getLong(KEY_USAGE_SECONDS, 0L)
    }

    fun checkDailyReset() {
        val today = dateFormat.format(Date())
        val savedDate = prefs.getString(KEY_LAST_DATE, "")
        if (savedDate != today) {
            prefs.edit()
                .putString(KEY_LAST_DATE, today)
                .putLong(KEY_USAGE_SECONDS, 0L)
                .putBoolean(KEY_LIMIT_DISMISSED, false)
                .apply()
            _usageSecondsToday.value = 0L
            _isLimitReached.value = false
        }
    }

    fun updateSettings(newSettings: ParentSettings) {
        prefs.edit()
            .putBoolean(KEY_SOUND_ENABLED, newSettings.soundEnabled)
            .putInt(KEY_LIMIT_MINUTES, newSettings.screenTimeLimitMinutes)
            .apply()
        _settings.value = newSettings
        evaluateLimit()
        if (isForeground) {
            if (newSettings.screenTimeLimitMinutes > 0) {
                startTicker()
            } else {
                stopTicker()
            }
        }
    }

    fun onAppForegrounded() {
        isForeground = true
        checkDailyReset()
        evaluateLimit()
        if (_settings.value.screenTimeLimitMinutes > 0) {
            startTicker()
        }
    }

    fun onAppBackgrounded() {
        isForeground = false
        stopTicker()
        saveUsage()
    }

    fun saveUsage() {
        prefs.edit().putLong(KEY_USAGE_SECONDS, _usageSecondsToday.value).apply()
    }

    fun startTicker() {
        tickerJob?.cancel()
        val limitMinutes = _settings.value.screenTimeLimitMinutes
        if (limitMinutes <= 0) {
            _isLimitReached.value = false
            return
        }

        tickerJob = scope.launch(Dispatchers.Main) {
            while (isActive && isForeground) {
                delay(1000L)
                checkDailyReset()
                if (_settings.value.screenTimeLimitMinutes > 0) {
                    val updated = _usageSecondsToday.value + 1L
                    _usageSecondsToday.value = updated
                    if (updated % 5L == 0L) {
                        saveUsage()
                    }
                    evaluateLimit()
                }
            }
        }
    }

    fun stopTicker() {
        tickerJob?.cancel()
        tickerJob = null
    }

    fun evaluateLimit() {
        val limitMinutes = _settings.value.screenTimeLimitMinutes
        if (limitMinutes <= 0) {
            _isLimitReached.value = false
            return
        }
        val isDismissed = prefs.getBoolean(KEY_LIMIT_DISMISSED, false)
        if (isDismissed) {
            _isLimitReached.value = false
            return
        }
        val limitSeconds = limitMinutes * 60L
        _isLimitReached.value = _usageSecondsToday.value >= limitSeconds
    }

    fun dismissReminderForToday() {
        prefs.edit().putBoolean(KEY_LIMIT_DISMISSED, true).apply()
        _isLimitReached.value = false
    }

    fun addExtraTimeMinutes(minutes: Int) {
        val currentLimit = _settings.value.screenTimeLimitMinutes
        val base = if (currentLimit <= 0) 15 else currentLimit
        val newLimit = base + minutes
        updateSettings(_settings.value.copy(screenTimeLimitMinutes = newLimit))
        prefs.edit().putBoolean(KEY_LIMIT_DISMISSED, false).apply()
        _isLimitReached.value = false
    }

    fun resetUsageData() {
        prefs.edit()
            .putLong(KEY_USAGE_SECONDS, 0L)
            .putBoolean(KEY_LIMIT_DISMISSED, false)
            .apply()
        _usageSecondsToday.value = 0L
        _isLimitReached.value = false
    }

    /**
     * For unit tests: manually simulate elapsed active seconds.
     */
    fun simulateUsageSeconds(seconds: Long) {
        _usageSecondsToday.value = seconds
        saveUsage()
        evaluateLimit()
    }
}
