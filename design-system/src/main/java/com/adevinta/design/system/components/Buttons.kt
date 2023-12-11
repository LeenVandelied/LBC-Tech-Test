package com.adevinta.design.system.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adevinta.design.system.theme.LBCTechTestTheme

object DsButton {
    @Composable
    fun PrimaryButton(
        modifier: Modifier = Modifier,
        text: String,
        tagId: String,
        isLoading: Boolean = false,
        isEnabled: Boolean = true,
        onClick: () -> Unit
    ) = DsButtonImpl(
        modifier = modifier,
        text = text,
        tagId = tagId,
        isLoading = isLoading,
        isEnabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        textColor = MaterialTheme.colorScheme.onPrimary,
        onClick = onClick
    )

    @Composable
    fun TextButton(
        modifier: Modifier = Modifier,
        text: String,
        tagId: String,
        textColor: Color = MaterialTheme.colorScheme.primary,
        isLoading: Boolean = false,
        isEnabled: Boolean = true,
        onClick: () -> Unit
    ) = DsButtonImpl(
        modifier = modifier,
        text = text,
        tagId = tagId,
        isLoading = isLoading,
        isEnabled = isEnabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = textColor,
            containerColor = Color.Transparent
        ),
        textColor = textColor,
        onClick = onClick
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun DsButtonImpl(
    modifier: Modifier,
    text: String,
    tagId: String,
    isLoading: Boolean,
    isEnabled: Boolean,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    textColor: Color,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .height(56.dp)
            .semantics {
                testTagsAsResourceId = true
            }
            .testTag(tagId),
        colors = colors,
        enabled = isEnabled,
        shape = MaterialTheme.shapes.small,
        onClick = {
            onClick()
        }) {
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(16.dp),
                    strokeWidth = 2.dp,
                    color = textColor
                )
            }
        } else {
            DsTexts.TextButton(
                text = text,
                color = textColor
            )
        }
    }
}

@Preview
@Composable
private fun PrimaryButtonPreview() {
    LBCTechTestTheme {
        DsButton.PrimaryButton(
            text = "Label",
            tagId = "",
            onClick = {})
    }
}