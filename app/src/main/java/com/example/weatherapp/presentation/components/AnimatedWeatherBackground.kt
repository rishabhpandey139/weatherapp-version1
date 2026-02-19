package com.example.weatherapp.presentation.components



import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.weatherapp.ui.theme.SkyBlue
import com.example.weatherapp.ui.theme.LightBlue
import com.example.weatherapp.ui.theme.CloudWhite

@Composable
fun AnimatedWeatherBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "weather_background_transition")

    // Animate the gradient colors
    val colorAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "color_animation"
    )

    // Create dynamic colors based on the animation value
    val gradientColors = listOf(
        SkyBlue.copy(alpha = 0.8f + (colorAnimationValue * 0.2f)),
        LightBlue.copy(alpha = 0.7f + (colorAnimationValue * 0.3f)),
        CloudWhite.copy(alpha = 0.9f)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = gradientColors
                )
            )
    ) {
        content()
    }
}
