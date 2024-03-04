package com.klewerro.kmmquiz.android.quiz.details.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.klewerro.kmmquiz.android.question.components.QuestionListItem
import com.klewerro.kmmquiz.domain.model.question.Question

@Composable
fun AnswerQuestionListItem(
    question: Question,
    showSaveButton: Boolean,
    modifier: Modifier = Modifier,
    selectedAnswerIndex: Int = -1,
    onAnswerSelected: (Int) -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer
) {
    QuestionListItem(
        question = question,
        showSaveButton = showSaveButton,
        modifier = modifier,
        selectedAnswerIndex = selectedAnswerIndex,
        backgroundColor = backgroundColor,
        onRadioButtonClick = onAnswerSelected
    )
}
