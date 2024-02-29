package com.klewerro.kmmquiz.android

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.klewerro.kmmquiz.SharedRes

@Composable
fun MyApplicationTheme(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val colors =
        lightColorScheme(
            primary = Color(SharedRes.colors.primary.getColor(context)),
            onPrimary = Color(SharedRes.colors.onPrimary.getColor(context)),
            primaryContainer = Color(SharedRes.colors.primaryContainer.getColor(context)),
            onPrimaryContainer = Color(SharedRes.colors.onPrimaryContainer.getColor(context)),
            secondary = Color(SharedRes.colors.secondary.getColor(context)),
            onSecondary = Color(SharedRes.colors.onSecondary.getColor(context)),
            secondaryContainer = Color(SharedRes.colors.secondaryContainer.getColor(context)),
            onSecondaryContainer = Color(SharedRes.colors.onSecondaryContainer.getColor(context)),
            tertiary = Color(SharedRes.colors.tertiary.getColor(context)),
            onTertiary = Color(SharedRes.colors.onTertiary.getColor(context)),
            tertiaryContainer = Color(SharedRes.colors.tertiaryContainer.getColor(context)),
            onTertiaryContainer = Color(SharedRes.colors.onTertiaryContainer.getColor(context)),
            error = Color(SharedRes.colors.error.getColor(context)),
            onError = Color(SharedRes.colors.onError.getColor(context)),
            errorContainer = Color(SharedRes.colors.errorContainer.getColor(context)),
            onErrorContainer = Color(SharedRes.colors.onErrorContainer.getColor(context)),
            background = Color(SharedRes.colors.background.getColor(context)),
            onBackground = Color(SharedRes.colors.onBackground.getColor(context)),
            surface = Color(SharedRes.colors.surface.getColor(context)),
            onSurface = Color(SharedRes.colors.onSurface.getColor(context)),
            surfaceVariant = Color(SharedRes.colors.surfaceVariant.getColor(context)),
            onSurfaceVariant = Color(SharedRes.colors.onSurfaceVariant.getColor(context)),
            outline = Color(SharedRes.colors.outline.getColor(context))
        )

    val typography =
        Typography(
            bodyMedium =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
        )
    val shapes =
        Shapes(
            small = RoundedCornerShape(4.dp),
            medium = RoundedCornerShape(4.dp),
            large = RoundedCornerShape(0.dp)
        )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
