package com.adevinta.design.system.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.adevinta.design.system.theme.buttonLarge

object DsTexts {
    @Composable
    fun TextButton(
        modifier: Modifier = Modifier,
        text: String,
        color: Color = MaterialTheme.colorScheme.primary,
        align: TextAlign = TextAlign.Start
    ) =
        DsTextsImpl(
            modifier = modifier,
            text = text,
            color = color,
            style = MaterialTheme.typography.buttonLarge,
            align = align,
            maxLines = 1
        )

    @Composable
    fun TitleMedium(
        modifier: Modifier = Modifier,
        title: String,
        align: TextAlign = TextAlign.Start
    ) =
        DsTextsImpl(
            modifier = modifier,
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium,
            align = align,
            maxLines = 1
        )

    @Composable
    fun BodyMedium(
        modifier: Modifier = Modifier,
        title: String,
        color: Color = MaterialTheme.colorScheme.onBackground,
        align: TextAlign = TextAlign.Start,
        maxLines: Int = Int.MAX_VALUE
    ) =
        DsTextsImpl(
            modifier = modifier,
            text = title,
            color = color,
            style = MaterialTheme.typography.bodyMedium,
            align = align,
            maxLines = maxLines
        )

    @Composable
    fun BodySmall(
        modifier: Modifier = Modifier,
        title: String,
        color: Color = MaterialTheme.colorScheme.onBackground,
        align: TextAlign = TextAlign.Start,
        maxLines: Int = Int.MAX_VALUE
    ) =
        DsTextsImpl(
            modifier = modifier,
            text = title,
            color = color,
            style = MaterialTheme.typography.bodySmall,
            align = align,
            maxLines = maxLines
        )
}

@Composable
private fun DsTextsImpl(
    modifier: Modifier,
    text: String,
    color: Color,
    style: TextStyle,
    align: TextAlign = TextAlign.Start,
    maxLines: Int,
    overflow: TextOverflow = TextOverflow.Clip,
    textDecoration: TextDecoration = TextDecoration.None
) {
    Text(
        modifier = modifier,
        text = text,
        style = style,
        color = color,
        textAlign = align,
        maxLines = maxLines,
        overflow = overflow,
        textDecoration = textDecoration,
    )
}
