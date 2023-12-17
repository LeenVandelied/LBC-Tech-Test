package com.adevinta.design.system.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adevinta.design.system.R
import com.adevinta.design.system.components.DsButton
import com.adevinta.design.system.components.DsTexts
import com.adevinta.design.system.theme.LBCTechTestTheme

object DsEmptyStateScreen {
    @Composable
    fun Loading(modifier: Modifier = Modifier, @StringRes title: Int, @StringRes description: Int) =
        DsEmptyStateScreenImpl(
            modifier = modifier,
            title = stringResource(id = title),
            description = stringResource(id = description)
        )

    @Composable
    fun Error(
        modifier: Modifier = Modifier,
        @StringRes title: Int,
        @StringRes description: Int,
        @StringRes buttonText: Int? = null,
        onButtonClick: (() -> Unit)? = null
    ) =
        DsEmptyStateScreenImpl(
            modifier = modifier,
            title = stringResource(id = title),
            description = stringResource(id = description),
            buttonText = buttonText?.let { stringResource(id = it) },
            onButtonClick = onButtonClick
        )

    @Composable
    fun Empty(
        modifier: Modifier = Modifier,
        @StringRes title: Int,
        @StringRes description: Int,
        @StringRes buttonText: Int? = null,
        onButtonClick: (() -> Unit)? = null
    ) =
        DsEmptyStateScreenImpl(
            modifier = modifier,
            title = stringResource(id = title),
            description = stringResource(id = description),
            buttonText = buttonText?.let { stringResource(id = it) },
            onButtonClick = onButtonClick
        )
}

@Composable
private fun DsEmptyStateScreenImpl(
    modifier: Modifier,
    title: String,
    description: String,
    buttonText: String? = null,
    onButtonClick: (() -> Unit)? = null
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.size(8.dp))
        DsTexts.TitleLarge(title = title)
        Spacer(modifier = Modifier.size(8.dp))
        DsTexts.BodyMedium(title = description)
        Spacer(modifier = Modifier.size(8.dp))
        if (buttonText != null && onButtonClick != null) {
            DsButton.ErrorButton(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                text = buttonText,
            ) {
                onButtonClick()
            }
        }
    }
}

@Preview
@Composable
private fun UpdatePreview() {
    LBCTechTestTheme {
        Surface {
            DsEmptyStateScreen.Error(
                title = R.string.dummy_title,
                description = R.string.dummy_subtitle,
                buttonText = R.string.dummy_button,
                onButtonClick = {}
            )
        }
    }
}
