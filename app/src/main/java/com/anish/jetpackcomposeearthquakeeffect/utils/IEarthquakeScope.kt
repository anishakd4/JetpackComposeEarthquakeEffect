package com.anish.jetpackcomposeearthquakeeffect.utils

interface IEarthquakeScope {
    fun startShaking()
    fun stopShaking()
    val isShaking: Boolean
    var shakeDuration: Long
    var shakesPerSecond: Int
    var shakeForce: Int
}