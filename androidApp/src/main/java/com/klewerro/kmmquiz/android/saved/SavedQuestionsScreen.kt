package com.klewerro.kmmquiz.android.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.klewerro.kmmquiz.android.question.components.QuestionListItem
import com.klewerro.kmmquiz.presentation.saved.SavedQuestionsEvent
import com.klewerro.kmmquiz.presentation.saved.SavedQuestionsState

@Composable
fun SavedQuestionsScreen(
    state: SavedQuestionsState,
    onEvent: (SavedQuestionsEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.savedQuestions) { question ->
                QuestionListItem(question = question, onSaveButtonClick = {})
            }
        }
    }
}
