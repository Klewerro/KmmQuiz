package com.klewerro.kmmquiz.android.question.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.klewerro.kmmquiz.android.MyApplicationTheme

@Composable
fun ProgressButton(isLoading: Boolean, text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        AnimatedContent(targetState = isLoading, label = "Progress button") {
            if (it) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    strokeWidth = 2.dp
                )
            } else {
                Text(text = text)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewProgressButtonLoading() {
    MyApplicationTheme {
        ProgressButton(isLoading = true, text = "Button text", onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewProgressButtonNotLoading() {
    MyApplicationTheme {
        ProgressButton(isLoading = false, text = "Button text", onClick = {})
    }
}
