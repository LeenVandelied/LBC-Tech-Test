package com.adevinta.design.system.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

object DsSnackbars {
    @Composable
    fun SnackbarError(modifier: Modifier = Modifier, message: String) =
        SnackbarImpl(
            modifier = modifier,
            message = message,
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError
        )
}

@Composable
private fun SnackbarImpl(
    modifier: Modifier,
    message: String,
    containerColor: Color,
    contentColor: Color
) {
    Snackbar(modifier = modifier, containerColor = containerColor, contentColor = contentColor) {
        DsTexts.BodyMedium(title = message, color = contentColor)
    }
}
