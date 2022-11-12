package com.anish.jetpackcomposeearthquakeeffect.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.anish.jetpackcomposeearthquakeeffect.R

@Composable
fun EarthquakeApp() {
    EarthquakeBox(
        onEarthquakeFinished = {
            println("finished")
        }
    ) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .border(2.dp, MaterialTheme.colors.onBackground)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    (1..5).map {
                        Text(text = "Item$it", color = MaterialTheme.colors.onBackground)
                    }
                }

                Text(text = "Shake Details:", color = MaterialTheme.colors.onBackground)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Field(
                        modifier = Modifier.weight(1f),
                        label = "Duration",
                        defaultValue = shakeDuration,
                        onValueChange = { shakeDuration = it },
                        condition = { it >= 0 },
                        changeStep = 1000
                    )
                    Field(
                        modifier = Modifier.weight(1f),
                        label = "Force",
                        defaultValue = shakeForce.toLong(),
                        onValueChange = { shakeForce = it.toInt() },
                        condition = { it >= 1 },
                        changeStep = 1
                    )
                    Field(
                        modifier = Modifier.weight(1f),
                        label = "Per Second",
                        defaultValue = shakesPerSecond.toLong(),
                        onValueChange = { shakesPerSecond = it.toInt() },
                        condition = { it >= 1 },
                        changeStep = 1
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = { if (!isShaking) startShaking() }) {
                        Text(text = "Start", color = MaterialTheme.colors.onPrimary)
                    }
                    Button(onClick = { if (isShaking) stopShaking() }) {
                        Text(text = "Stop", color = MaterialTheme.colors.onPrimary)
                    }
                }
            }
        }
    }
}


@Composable
fun Field(
    modifier: Modifier = Modifier,
    label: String,
    defaultValue: Long,
    changeStep: Long,
    condition: (Long) -> Boolean,
    onValueChange: (Long) -> Unit
) {
    var value by remember { mutableStateOf(defaultValue) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = label, color = MaterialTheme.colors.onBackground)

        Button(onClick = {
            val newValue = value + changeStep
            if (condition(newValue)) {
                value = newValue
                onValueChange(newValue)
            }
        }) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_up),
                contentDescription = "Increase",
                colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onPrimary)
            )
        }
        Text(text = value.toString(), color = MaterialTheme.colors.onBackground)
        Button(onClick = {
            val newValue = value - changeStep
            if (condition(newValue)) {
                value = newValue
                onValueChange(newValue)
            }
        }) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "Decrease",
                colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onPrimary)
            )
        }
    }
}

