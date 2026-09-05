package com.example.studentapp2.ui.theme // TODO: replace with your actual package name

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val MyGpmLightColorScheme = lightColorScheme(
    primary = MyGpmPrimary,
    onPrimary = MyGpmOnPrimary,
    background = MyGpmBackground,
    surface = MyGpmSurface,
    onSurface = MyGpmTextPrimary,
    onSurfaceVariant = MyGpmTextSecondary,
    error = MyGpmError,
    outline = MyGpmBorder
)

// Rounded corners are a key part of the reference (pill button, rounded fields/cards)
val MyGpmShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),   // input fields
    large = RoundedCornerShape(24.dp),    // login card
    extraLarge = RoundedCornerShape(28.dp) // login button (pill-ish)
)

/**
 * If this project already has a theme file (e.g. an existing MyGPMTheme / AppTheme),
 * do NOT create a second one — merge these color/typography/shape values into it instead.
 */
@Composable
fun MyGPMTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MyGpmLightColorScheme,
        typography = MyGpmTypography,
        shapes = MyGpmShapes,
        content = content
    )
}