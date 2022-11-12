package com.anish.jetpackcomposeearthquakeeffect.utils

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Stable
class EarthquakeState {
    var isShaking by mutableStateOf(false)
    var earthquakeDuration by mutableStateOf(2000L)
    var shakesPerSecond by mutableStateOf(10)
    var shakeForce by mutableStateOf(10)
}