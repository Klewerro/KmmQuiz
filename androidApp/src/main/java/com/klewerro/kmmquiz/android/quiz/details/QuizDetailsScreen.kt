package com.klewerro.kmmquiz.android.quiz.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.klewerro.kmmquiz.SharedRes
import com.klewerro.kmmquiz.android.MyApplicationTheme
import com.klewerro.kmmquiz.android.core.sharedStringResource
import com.klewerro.kmmquiz.android.quiz.details.components.AnswerQuestionListItem
import com.klewerro.kmmquiz.domain.model.Quiz
import com.klewerro.kmmquiz.domain.model.question.Question
import com.klewerro.kmmquiz.domain.model.question.QuestionDifficulty
import com.klewerro.kmmquiz.domain.model.question.QuestionType
import com.klewerro.kmmquiz.presentation.quiz.details.QuizDetailsEvent
import com.klewerro.kmmquiz.presentation.quiz.details.QuizDetailsState

@Composable
fun QuizDetailsScreen(
    state: QuizDetailsState,
    onEvent: (QuizDetailsEvent) -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        state.quiz?.let { quizValue ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = quizValue.title,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        sharedStringResource(
                            id = SharedRes.strings.quiz_progress,
                            state.answers.size,
                            quizValue.questions.size
                        )
                    )
                }
                Icon(
                    imageVector = Icons.Default.Cancel,
                    contentDescription = sharedStringResource(SharedRes.strings.content_description_cancel_quiz),
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(32.dp).clickable { onCloseClick() }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                state.quiz?.let { quizValue ->
                    itemsIndexed(quizValue.questions) { index, question ->
                        AnswerQuestionListItem(
                            question = question,
                            showSaveButton = false,
                            onAnswerSelected = { answerIndex ->
                                onEvent(QuizDetailsEvent.QuestionAnswered(index, answerIndex))
                            },
                            selectedAnswerIndex = question.allAnswers.indexOf(state.answers[index]?.answer)
                        )
                    }
                    item {
                        AnimatedVisibility(visible = state.areAllAnswersSelected()) {
                            Button(
                                onClick = {
                                    onEvent(QuizDetailsEvent.SaveAnswers)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 48.dp)
                            ) {
                                Text(sharedStringResource(SharedRes.strings.save_answers))
                            }
                        }
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun QuizDetailsScreenPreview() {
    MyApplicationTheme {
        val question = Question(
            "",
            QuestionDifficulty.MEDIUM,
            "correct answer",
            listOf("incorrect1", "incorrect2", "incorrect3"),
            QuestionType.MULTIPLE,
            "Question text"
        )
        val state = QuizDetailsState(
            quiz = Quiz(
                1L,
                "Quiz title",
                kotlinx.datetime.LocalDateTime(2020, 1, 1, 20, 22),
                listOf(question, question, question)
            )
        )
        QuizDetailsScreen(state, {}, {})
    }
}
