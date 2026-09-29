package com.example.ui.components

import android.os.SystemClock

/**
 * Singleton state holder for Parental Gate rate-limiting and security cooldown.
 *
 * Enforces:
 * - Up to 3 incorrect attempts.
 * - 30-second cooldown after 3 failed attempts.
 * - Shared state across all Parent Gate entry points (Parent Zone, Screen Time unlock).
 * - Monotonic clock (SystemClock.elapsedRealtime) to prevent device clock bypass.
 */
object ParentalGateSecurity {
    const val MAX_FAILED_ATTEMPTS = 3
    const val COOLDOWN_DURATION_MS = 30_000L

    var failedAttempts: Int = 0
        private set

    var cooldownUntilElapsedRealtime: Long = 0L
        private set

    // Pluggable monotonic clock provider (defaults to SystemClock.elapsedRealtime)
    var clock: () -> Long = { SystemClock.elapsedRealtime() }

    fun currentElapsedRealtime(): Long = clock()

    fun isCoolingDown(): Boolean {
        checkAndResetIfExpired()
        return currentElapsedRealtime() < cooldownUntilElapsedRealtime
    }

    fun remainingCooldownSeconds(): Int {
        checkAndResetIfExpired()
        val remainingMs = cooldownUntilElapsedRealtime - currentElapsedRealtime()
        return if (remainingMs > 0) ((remainingMs + 999) / 1000).toInt() else 0
    }

    /**
     * Records a failed attempt. If failed attempts reach MAX_FAILED_ATTEMPTS,
     * initiates the 30-second cooldown.
     * Returns true if cooldown is now active.
     */
    fun recordFailedAttempt(): Boolean {
        if (isCoolingDown()) {
            return true
        }
        failedAttempts++
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            cooldownUntilElapsedRealtime = currentElapsedRealtime() + COOLDOWN_DURATION_MS
            return true
        }
        return false
    }

    /**
     * Resets failed attempts and cooldown when the parent enters the correct answer.
     */
    fun recordSuccess() {
        failedAttempts = 0
        cooldownUntilElapsedRealtime = 0L
    }

    /**
     * Checks if the active cooldown has expired and resets attempts if so.
     * Returns true if a previously active cooldown has expired and was reset.
     */
    fun checkAndResetIfExpired(): Boolean {
        val now = currentElapsedRealtime()
        if (cooldownUntilElapsedRealtime > 0L && now >= cooldownUntilElapsedRealtime) {
            failedAttempts = 0
            cooldownUntilElapsedRealtime = 0L
            return true
        }
        return false
    }

    /**
     * Resets all security state for testing purposes.
     */
    fun resetForTesting() {
        failedAttempts = 0
        cooldownUntilElapsedRealtime = 0L
        clock = { SystemClock.elapsedRealtime() }
    }
}
