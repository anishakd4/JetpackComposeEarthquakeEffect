package com.anish.jetpackcomposeearthquakeeffect.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.anish.jetpackcomposeearthquakeeffect.utils.*

@Composable
fun EarthquakeBox(
    onEarthquakeFinished: () -> Unit = {},
    content: @Composable IEarthquakeScope.() -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    val state = remember { EarthquakeState() }
    val scope = remember { EarthquakeScope(state = state) }
    val mover = remember { EarthquakeMover() }
    val controller = remember {
        EarthquakeController(
            scope = coroutineScope,
            mover = mover,
            onEarthquakeFinished = {
                state.isShaking = false
                onEarthquakeFinished()
            }
        )
    }

    LaunchedEffect(state.isShaking) {
        if (state.isShaking) {
            controller.startShaking(
                earthquakeDuration = state.earthquakeDuration,
                shakeDuration = 1000L / state.shakesPerSecond,
                shakeForce = state.shakeForce
            )
        } else {
            controller.stopShaking()
        }
    }

    Box(
        modifier = Modifier
            .alpha(mover.alpha.value)
            .offset(mover.x.value.dp, mover.y.value.dp)
            .rotate(mover.rotation.value)
            .padding(state.shakeForce.dp)
    ) {
        scope.content()
    }
}