package com.klewerro.kmmquiz.android.saved

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.klewerro.kmmquiz.presentation.saved.SavedQuestionsEvent
import com.klewerro.kmmquiz.presentation.saved.SavedQuestionsState

@Composable
fun SavedQuestionsScreen(
    state: SavedQuestionsState,
    onEvent: (SavedQuestionsEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Text(text = "Saved questions screen")
    }
}
