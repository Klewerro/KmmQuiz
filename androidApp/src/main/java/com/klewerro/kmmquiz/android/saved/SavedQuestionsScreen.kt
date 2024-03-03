package com.klewerro.kmmquiz.android.saved

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.klewerro.kmmquiz.SharedRes
import com.klewerro.kmmquiz.SharedStrings
import com.klewerro.kmmquiz.android.MyApplicationTheme
import com.klewerro.kmmquiz.android.saved.components.SaveQuizHeader
import com.klewerro.kmmquiz.android.saved.components.SelectableSavedQuestionListItem
import com.klewerro.kmmquiz.domain.model.question.Question
import com.klewerro.kmmquiz.domain.model.question.QuestionDifficulty
import com.klewerro.kmmquiz.domain.model.question.QuestionType
import com.klewerro.kmmquiz.presentation.saved.SavedQuestionsEvent
import com.klewerro.kmmquiz.presentation.saved.SavedQuestionsState
import com.klewerro.kmmquiz.presentation.saved.SelectableSavedQuestion

@Composable
fun SavedQuestionsScreen(
    state: SavedQuestionsState,
    onEvent: (SavedQuestionsEvent) -> Unit,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(state.saveQuizResult) {
        state.saveQuizResult?.let { isSavedSuccessfully ->
            val message = if (isSavedSuccessfully) {
                SharedStrings(context).get(SharedRes.strings.quiz_created_successfully, emptyList())
            } else {
                SharedStrings(context).get(SharedRes.strings.quiz_creation_error, emptyList())
            }
            snackbarHostState.showSnackbar(message)
            onEvent(SavedQuestionsEvent.OnQuizCreateMessageSeen)
        }
    }

    Box(modifier = modifier) {
        AnimatedVisibility(
            visible = state.savedQuestions.any { it.isSelected },
            enter = expandHorizontally(),
            exit = shrinkHorizontally(),
            modifier = Modifier
                .zIndex(0.1f)
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {
            Button(
                onClick = { /*Do nothing*/ }
            ) {
                Text(
                    text = state.savedQuestions.count { it.isSelected }.toString(),
                    fontSize = 18.sp
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                SaveQuizHeader(
                    state.savedQuestions.any { it.isSelected },
                    state.newQuizName,
                    onTextChange = {
                        onEvent(SavedQuestionsEvent.ChangeQuizNameText(it))
                    },
                    onSaveButtonClick = {
                        keyboardController?.hide()
                        onEvent(SavedQuestionsEvent.SaveQuiz)
                    }
                )
            }
            items(state.savedQuestions) { selectableSavedQuestion ->
                SelectableSavedQuestionListItem(
                    selectableSavedQuestion = selectableSavedQuestion,
                    onDeleteClick = {
                        onEvent(SavedQuestionsEvent.DeleteQuestion(selectableSavedQuestion.question))
                    },
                    onSelectedChanged = {
                        onEvent(SavedQuestionsEvent.QuestionSelectionChanged(selectableSavedQuestion, it))
                    }
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewSavedQuestionsScreen() {
    val question = Question(
        "",
        QuestionDifficulty.MEDIUM,
        "correct answer",
        listOf("incorrect1", "incorrect2", "incorrect3"),
        QuestionType.MULTIPLE,
        "Question text"
    )
    MyApplicationTheme {
        SavedQuestionsScreen(
            state = SavedQuestionsState(
                savedQuestions = listOf(
                    SelectableSavedQuestion(question, false),
                    SelectableSavedQuestion(question, true),
                    SelectableSavedQuestion(question, true),
                    SelectableSavedQuestion(question, false)
                )
            ),
            onEvent = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}
