package com.adevinta.design.system.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

object DsSnackbars {
    @Composable
    fun SnackbarError(
        modifier: Modifier = Modifier,
        message: String,
        actionLabel: String? = null,
        tagId: String,
        actionClick: (() -> Unit)? = null
    ) = SnackbarImpl(
        modifier = modifier,
        message = message,
        actionLabel = actionLabel,
        containerColor = MaterialTheme.colorScheme.error,
        contentColor = MaterialTheme.colorScheme.onError,
        tagId = tagId,
        actionClick = actionClick,
    )
}

@Composable
private fun SnackbarImpl(
    modifier: Modifier,
    message: String,
    actionLabel: String? = null,
    containerColor: Color,
    contentColor: Color,
    tagId: String,
    actionClick: (() -> Unit)? = null
) {
    Snackbar(
        modifier = modifier,
        action = {
            if (actionLabel != null && actionClick != null) {
                DsButton.TextButton(
                    text = actionLabel,
                    tagId = tagId,
                    textColor = contentColor,
                    onClick = actionClick
                )
            }
        },
        containerColor = containerColor,
        contentColor = contentColor
    ) {
        DsTexts.BodyMedium(title = message, color = contentColor)
    }
}