package com.example.composetutorial

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

// Light sensor class modified from ChatGPT 5.0 generated temperature sensor class

class LightSensor (context: Context, private val onChanged: (Float) -> Unit) : SensorEventListener{

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val lightSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

    fun start() {
        lightSensor?.let {
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val light = event.values[0]
        onChanged(light)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

}