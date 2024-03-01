package com.klewerro.kmmquiz.android.question

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.klewerro.kmmquiz.SharedRes
import com.klewerro.kmmquiz.android.MyApplicationTheme
import com.klewerro.kmmquiz.android.core.sharedStringResource
import com.klewerro.kmmquiz.android.question.components.ProgressButton
import com.klewerro.kmmquiz.presentation.question.GetQuestionEvent
import com.klewerro.kmmquiz.presentation.question.GetQuestionState

@Composable
fun GetQuestionListScreen(state: GetQuestionState, onEvent: (GetQuestionEvent) -> Unit, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        ProgressButton(
            isLoading = state.isFetchingData,
            text = sharedStringResource(id = SharedRes.strings.get_questions),
            onClick = { onEvent(GetQuestionEvent.GetNewQuestion) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    val state = GetQuestionState(
        false,
        emptyList(),
        null
    )
    MyApplicationTheme {
        GetQuestionListScreen(state = state, onEvent = {}, modifier = Modifier.fillMaxSize())
    }
}
