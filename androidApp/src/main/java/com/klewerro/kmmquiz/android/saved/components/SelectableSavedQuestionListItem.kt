package com.klewerro.kmmquiz.android.saved.components

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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.klewerro.kmmquiz.SharedRes
import com.klewerro.kmmquiz.android.core.sharedStringResource
import com.klewerro.kmmquiz.domain.model.question.Question
import com.klewerro.kmmquiz.domain.model.question.QuestionDifficulty
import com.klewerro.kmmquiz.domain.model.question.QuestionType
import com.klewerro.kmmquiz.presentation.saved.SelectableSavedQuestion

@Composable
fun SelectableSavedQuestionListItem(
    selectableSavedQuestion: SelectableSavedQuestion,
    onDeleteClick: () -> Unit,
    onSelectedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColorSelected: Color = MaterialTheme.colorScheme.secondaryContainer,
    backgroundColorUnselected: Color = MaterialTheme.colorScheme.tertiaryContainer,
    onBackgroundColorSelected: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    onBackgroundColorUnselected: Color = MaterialTheme.colorScheme.onTertiaryContainer
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onSelectedChanged(!selectableSavedQuestion.isSelected)
            }
            .clip(RoundedCornerShape(8.dp))
            .background(
                if (selectableSavedQuestion.isSelected) {
                    backgroundColorSelected
                } else {
                    backgroundColorUnselected
                }
            )
            .padding(8.dp)
    ) {
        var selectedItemIndex by remember {
            mutableIntStateOf(-1)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = selectableSavedQuestion.question.text,
                fontWeight = FontWeight.Bold,
                color = if (selectableSavedQuestion.isSelected) {
                    onBackgroundColorSelected
                } else {
                    onBackgroundColorUnselected
                },
                modifier = Modifier
                    .padding(end = 8.dp)
                    .alignByBaseline()
                    .weight(1f)
            )
            Button(
                onClick = onDeleteClick,
                modifier = Modifier
                    .size(50.dp) // 50dp for equal circle shape
                    .alignByBaseline(),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = sharedStringResource(SharedRes.strings.content_description_delete_question)
                )
            }
        }
        selectableSavedQuestion.question.allAnswers.forEachIndexed { index, answerText ->
            Row(
                modifier = Modifier.clickable {
                    selectedItemIndex = index
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = index == selectedItemIndex,
                    onClick = {
                        selectedItemIndex = index
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = if (selectableSavedQuestion.isSelected) {
                            onBackgroundColorSelected
                        } else {
                            onBackgroundColorUnselected
                        },
                        unselectedColor = MaterialTheme.colorScheme.outline
                    )
                )
                Text(
                    text = answerText,
                    color = if (selectableSavedQuestion.isSelected) {
                        onBackgroundColorSelected
                    } else {
                        onBackgroundColorUnselected
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectableSavedQuestionListItemSelected() {
    SelectableSavedQuestionListItem(
        selectableSavedQuestion = SelectableSavedQuestion(
            Question(
                category = "category",
                difficulty = QuestionDifficulty.MEDIUM,
                correctAnswer = "correct answer",
                incorrectAnswers = listOf(
                    "incorrect 1",
                    "incorrect 3",
                    "incorrect 2"
                ),
                type = QuestionType.MULTIPLE,
                text = "Qestion text"
            ),
            isSelected = true
        ),
        onDeleteClick = {},
        onSelectedChanged = {}
    )
}

@Preview(showBackground = true)
@Composable
fun SelectableSavedQuestionListItemNotSelected() {
    SelectableSavedQuestionListItem(
        selectableSavedQuestion = SelectableSavedQuestion(
            Question(
                category = "category",
                difficulty = QuestionDifficulty.MEDIUM,
                correctAnswer = "correct answer",
                incorrectAnswers = listOf(
                    "incorrect 1",
                    "incorrect 3",
                    "incorrect 2"
                ),
                type = QuestionType.MULTIPLE,
                text = "Qestion text"
            ),
            isSelected = false
        ),
        onDeleteClick = {},
        onSelectedChanged = {}
    )
}
