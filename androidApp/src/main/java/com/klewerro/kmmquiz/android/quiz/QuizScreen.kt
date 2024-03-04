package com.klewerro.kmmquiz.android.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.klewerro.kmmquiz.android.MyApplicationTheme
import com.klewerro.kmmquiz.android.quiz.components.QuizListItem
import com.klewerro.kmmquiz.domain.model.Quiz
import com.klewerro.kmmquiz.presentation.quiz.QuizEvent
import com.klewerro.kmmquiz.presentation.quiz.QuizState
import java.time.LocalDateTime

@Composable
fun QuizScreen(state: QuizState, onEvent: (QuizEvent) -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.quizList) { quiz ->
                QuizListItem(quiz)
            }
        }
    }
}

@PreviewLightDark
@Composable
fun QuizScreenPreview() {
    val exampleQuiz = Quiz(
        1L,
        "Quiz title",
        kotlinx.datetime.LocalDateTime(2020, 1, 1, 20, 22),
        emptyList()
    )
    MyApplicationTheme {
        QuizScreen(
            state = QuizState(
                listOf(
                    exampleQuiz,
                    exampleQuiz
                )
            ),
            onEvent = {}
        )
    }
}
