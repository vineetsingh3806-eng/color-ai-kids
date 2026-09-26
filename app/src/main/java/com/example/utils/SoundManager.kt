package com.example.utils

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.sin

/**
 * Lightweight kid-friendly tone synthesizer for cheerful feedback sounds:
 * - Button taps
 * - Fill bucket splash
 * - Brush stroke whoosh
 * - Star reward chimes
 * - Celebration fanfare
 */
class SoundManager(private val scope: CoroutineScope) {

    var isEnabled: Boolean = true

    fun playTap() {
        if (!isEnabled) return
        playTone(frequency = 587.33f, durationMs = 60, volume = 0.35f) // D5
    }

    fun playFill() {
        if (!isEnabled) return
        scope.launch(Dispatchers.Default) {
            playToneSync(frequency = 523.25f, durationMs = 50, volume = 0.4f) // C5
            playToneSync(frequency = 659.25f, durationMs = 90, volume = 0.45f) // E5
        }
    }

    fun playUndo() {
        if (!isEnabled) return
        scope.launch(Dispatchers.Default) {
            playToneSync(frequency = 659.25f, durationMs = 50, volume = 0.3f)
            playToneSync(frequency = 523.25f, durationMs = 70, volume = 0.25f)
        }
    }

    fun playStarChime() {
        if (!isEnabled) return
        scope.launch(Dispatchers.Default) {
            val notes = listOf(523.25f, 659.25f, 783.99f, 1046.50f) // C5, E5, G5, C6
            for (note in notes) {
                playToneSync(frequency = note, durationMs = 90, volume = 0.45f)
            }
        }
    }

    fun playCelebration() {
        if (!isEnabled) return
        scope.launch(Dispatchers.Default) {
            val melody = listOf(
                523.25f to 100, // C5
                659.25f to 100, // E5
                783.99f to 120, // G5
                1046.50f to 260 // C6
            )
            for ((freq, dur) in melody) {
                playToneSync(frequency = freq, durationMs = dur, volume = 0.5f)
            }
        }
    }

    private fun playTone(frequency: Float, durationMs: Int, volume: Float) {
        scope.launch(Dispatchers.Default) {
            playToneSync(frequency, durationMs, volume)
        }
    }

    private fun playToneSync(frequency: Float, durationMs: Int, volume: Float) {
        try {
            val sampleRate = 22050
            val numSamples = (sampleRate * (durationMs / 1000.0)).toInt().coerceAtLeast(1)
            val buffer = ShortArray(numSamples)

            for (i in 0 until numSamples) {
                val time = i.toDouble() / sampleRate
                // Sine wave with soft envelope to avoid click sounds
                val envelope = when {
                    i < numSamples * 0.1 -> i / (numSamples * 0.1)
                    i > numSamples * 0.8 -> (numSamples - i) / (numSamples * 0.2)
                    else -> 1.0
                }
                val sample = (sin(2.0 * Math.PI * frequency * time) * Short.MAX_VALUE * volume * envelope).toInt()
                buffer[i] = sample.coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
            }

            val audioTrack = AudioTrack.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_GAME)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                )
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(sampleRate)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build()
                )
                .setBufferSizeInBytes(buffer.size * 2)
                .setTransferMode(AudioTrack.MODE_STATIC)
                .build()

            audioTrack.write(buffer, 0, buffer.size)
            audioTrack.play()
            Thread.sleep(durationMs.toLong())
            audioTrack.stop()
            audioTrack.release()
        } catch (_: Exception) {
            // Audio may fail on some restricted headless runners; ignore gracefully
        }
    }
}
