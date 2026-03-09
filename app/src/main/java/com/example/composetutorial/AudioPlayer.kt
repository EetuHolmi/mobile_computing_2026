package com.example.composetutorial

import android.media.MediaPlayer
import android.util.Log

// AudioPlayer helper class generated using ChatGPT 5 model

class AudioPlayer {

    private var player: MediaPlayer? = null

    fun play(path: String) {

        try {

            player?.release()

            player = MediaPlayer().apply {
                setDataSource(path)
                prepare()
                start()
            }

        } catch (e: Exception) {
            Log.e("AudioPlayer", "Playback failed: ${e.message}")
        }
    }

    fun stop() {
        player?.release()
        player = null
    }
}