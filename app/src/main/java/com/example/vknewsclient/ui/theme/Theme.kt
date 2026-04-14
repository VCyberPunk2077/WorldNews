package com.example.vknewsclient.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    surfaceContainer = Black900,
    surface = Black900,
    onSurface = Color.White,
    secondaryContainer = Black900,
    onSecondaryContainer = Color.White,
    onSurfaceVariant = Black500
)

private val LightColorScheme = lightColorScheme(
    surfaceContainer = Color.White,
    surface = Color.White,
    onSurface = Black900,
    secondaryContainer = Color.White,
    onSecondaryContainer = Black900,
    onSurfaceVariant = Black500
)

@Composable
fun VkNewsClientTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}