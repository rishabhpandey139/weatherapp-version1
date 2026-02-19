package com.neatroots.weatherapps.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier,
    circleColor: Color = MaterialTheme.colorScheme.primary,
    circleSize: Dp = 12.dp,
    animationDelay: Int = 400,
    initialAlpha: Float = 0.3f
) {
    // 3 circles
    val circles = listOf(
        remember { Animatable(initialValue = initialAlpha) },
        remember { Animatable(initialValue = initialAlpha) },
        remember { Animatable(initialValue = initialAlpha) }
    )

    circles.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable) {
            delay(timeMillis = (animationDelay / circles.size).toLong() * index)

            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = animationDelay
                        initialAlpha at 0 with LinearEasing
                        1f at animationDelay / 2 with LinearEasing
                        initialAlpha at animationDelay with LinearEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    // container for circles
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // adding each circle
        circles.forEachIndexed { index, animatable ->
            val circleAlpha by animatable.asState()

            Box(
                modifier = Modifier
                    .size(circleSize)
                    .clip(CircleShape)
                    .background(circleColor.copy(alpha = circleAlpha))
            )

            // if not the last circle add a spacer
            if (index != circles.lastIndex) {
                Spacer(modifier = Modifier.width(6.dp))
            }
        }
    }
}
