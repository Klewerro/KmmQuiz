package com.klewerro.kmmquiz.android.question.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.klewerro.kmmquiz.SharedRes
import com.klewerro.kmmquiz.android.MyApplicationTheme
import com.klewerro.kmmquiz.android.core.sharedStringResource
import com.klewerro.kmmquiz.domain.model.question.Question
import com.klewerro.kmmquiz.domain.model.question.QuestionDifficulty
import com.klewerro.kmmquiz.domain.model.question.QuestionType

@Composable
fun QuestionListItem(
    question: Question,
    showSaveButton: Boolean,
    modifier: Modifier = Modifier,
    selectedAnswerIndex: Int = -1,
    onSaveButtonClick: () -> Unit = {},
    onRadioButtonClick: (Int) -> Unit = {},
    backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = question.text,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier
                    .alignByBaseline()
                    .weight(1f)
            )
            if (showSaveButton) {
                Button(
                    onClick = onSaveButtonClick,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(50.dp) // 50dp for equal circle shape
                        .alignByBaseline(),
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = sharedStringResource(SharedRes.strings.content_description_save_question)
                    )
                }
            }
        }
        question.allAnswers.forEachIndexed { index, answerText ->
            Row(
                modifier = Modifier.clickable {
                    onRadioButtonClick(index)
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = index == selectedAnswerIndex,
                    onClick = {
                        onRadioButtonClick(index)
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.secondary,
                        unselectedColor = MaterialTheme.colorScheme.outline
                    )
                )
                Text(
                    text = answerText,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionListItemPreview() {
    val question = Question(
        "General knowledge",
        difficulty = QuestionDifficulty.MEDIUM,
        correctAnswer = "Correct answer",
        incorrectAnswers = listOf(
            "Incorrect answer 1",
            "Incorrect answer 2",
            "Incorrect answer 3"
        ),
        type = QuestionType.MULTIPLE,
        text = "Question text"
    )
    MyApplicationTheme {
        QuestionListItem(
            question = question,
            showSaveButton = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BooleanQuestionListItemPreview() {
    val question = Question(
        "General knowledge",
        difficulty = QuestionDifficulty.MEDIUM,
        correctAnswer = "true",
        incorrectAnswers = listOf("false"),
        type = QuestionType.BOOLEAN,
        text = "Question text Question text Question text Question text Question text"
    )
    MyApplicationTheme {
        QuestionListItem(
            question = question,
            showSaveButton = true
        )
    }
}
