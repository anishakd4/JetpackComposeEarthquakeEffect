package com.anish.jetpackcomposeearthquakeeffect.utils

import androidx.compose.runtime.Immutable

@Immutable
interface IEarthquakeController {
    fun startShaking(earthquakeDuration: Long, shakeDuration: Long, shakeForce: Int)
    fun stopShaking()
}