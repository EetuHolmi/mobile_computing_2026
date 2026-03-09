package com.example.composetutorial

import android.content.Context
import android.media.MediaRecorder
import java.io.File

// AudioRecorder helper class generated using CHatGPT 5 model

class AudioRecorder(private val context: Context) {

    private var recorder: MediaRecorder? = null
    private var outputFile: String? = null

    fun startRecording(): String {

        val file = File(context.cacheDir, "audio_${System.currentTimeMillis()}.m4a")
        outputFile = file.absolutePath

        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setOutputFile(outputFile)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)

            prepare()
            start()
        }

        return outputFile!!
    }

    fun stopRecording(): String? {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
        return outputFile
    }
}