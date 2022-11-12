package com.anish.jetpackcomposeearthquakeeffect.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Offset

@Immutable
interface IEarthquakeMover {
    val x: Animatable<Float, AnimationVector1D>
    val y: Animatable<Float, AnimationVector1D>
    val rotation: Animatable<Float, AnimationVector1D>
    val alpha: Animatable<Float, AnimationVector1D>

    suspend fun move(shakeDuration: Int, shakeForce: Int)
    suspend fun stop()

    fun generateOffset(shakeForce: Int): Offset
    fun generateRotation(shakeForce: Int): Float
    fun generateAlpha(): Float
}