package com.klewerro.kmmquiz.android.question.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.klewerro.kmmquiz.android.MyApplicationTheme

@Composable
fun ProgressButton(
    isLoading: Boolean,
    labelText: String,
    progressText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        AnimatedContent(targetState = isLoading, label = "Progress button") {
            if (it) {
                Row {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = progressText)
                }
            } else {
                Text(text = labelText)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewProgressButtonLoading() {
    MyApplicationTheme {
        ProgressButton(isLoading = true, labelText = "Button text", progressText = "Progress text", onClick = {})
    }
}

@PreviewLightDark
@Composable
private fun PreviewProgressButtonNotLoading() {
    MyApplicationTheme {
        ProgressButton(isLoading = false, labelText = "Button text", progressText = "Progress text", onClick = {})
    }
}
