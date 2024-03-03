package com.klewerro.kmmquiz.android.question

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.klewerro.kmmquiz.SharedRes
import com.klewerro.kmmquiz.SharedStrings
import com.klewerro.kmmquiz.android.MyApplicationTheme
import com.klewerro.kmmquiz.android.core.sharedStringResource
import com.klewerro.kmmquiz.android.question.components.CategoryDropDown
import com.klewerro.kmmquiz.android.question.components.ProgressButton
import com.klewerro.kmmquiz.android.question.components.QuestionListItem
import com.klewerro.kmmquiz.domain.model.GetQuestionListError
import com.klewerro.kmmquiz.domain.model.question.Question
import com.klewerro.kmmquiz.domain.model.question.QuestionDifficulty
import com.klewerro.kmmquiz.domain.model.question.QuestionType
import com.klewerro.kmmquiz.presentation.question.GetQuestionListEvent
import com.klewerro.kmmquiz.presentation.question.GetQuestionListState

@Composable
fun GetQuestionListScreen(
    state: GetQuestionListState,
    onEvent: (GetQuestionListEvent) -> Unit,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(state.error) {
        val message = when (state.error) {
            GetQuestionListError.SERVICE_UNAVAILABLE -> SharedStrings(context)
                .get(SharedRes.strings.error_service_unavailable, emptyList())

            GetQuestionListError.CLIENT_ERROR,
            GetQuestionListError.SERIALIZATION_ERROR -> SharedStrings(context)
                .get(SharedRes.strings.client_error, emptyList())

            GetQuestionListError.TOO_MANY_REQUESTS_ERROR -> SharedStrings(context)
                .get(SharedRes.strings.too_many_requests_error, emptyList())

            GetQuestionListError.UNKNOWN_ERROR -> SharedStrings(context)
                .get(SharedRes.strings.unknown_error, emptyList())

            GetQuestionListError.AMOUNT_IS_0 -> SharedStrings(context)
                .get(SharedRes.strings.amount_is_0_error, emptyList())

            GetQuestionListError.SERVER_ERROR -> SharedStrings(context).get(SharedRes.strings.server_error, emptyList())
            null -> null
        }
        message?.let {
            snackbarHostState.showSnackbar(message)
            onEvent(GetQuestionListEvent.OnErrorSeen)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = if (state.amountOfQuestions == -1) {
                    ""
                } else {
                    state.amountOfQuestions.toString()
                },
                onValueChange = {
                    onEvent(GetQuestionListEvent.ChangeAmount(it))
                },
                label = {
                    Text(text = sharedStringResource(id = SharedRes.strings.number_of_questions))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onEvent(GetQuestionListEvent.GetNewQuestionList)
                        keyboardController?.hide()
                    }
                ),
                modifier = Modifier
                    .weight(1.0f)
                    .padding(16.dp)
            )
            CategoryDropDown(
                currentQuestionCategory = state.questionCategory,
                isOpen = state.isChoosingQuestionCategory,
                onClick = {
                    onEvent(GetQuestionListEvent.StartChoosingQuestionCategory)
                },
                onDismiss = {
                    onEvent(GetQuestionListEvent.StopChoosingQuestionCategory)
                },
                onCategoryClick = { questionCategory ->
                    onEvent(GetQuestionListEvent.ChangeQuestionCategory(questionCategory))
                },
                modifier = Modifier
                    .weight(1.0f)
                    .padding(16.dp)
            )
        }
        ProgressButton(
            isLoading = state.isFetchingData,
            labelText = sharedStringResource(id = SharedRes.strings.get_questions),
            progressText = sharedStringResource(id = SharedRes.strings.getting_questions),
            onClick = {
                onEvent(GetQuestionListEvent.GetNewQuestionList)
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.questions) { question ->
                QuestionListItem(question)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    val question = Question(
        "",
        QuestionDifficulty.MEDIUM,
        "correct answer",
        listOf("incorrect1", "incorrect2", "incorrect3"),
        QuestionType.MULTIPLE,
        "Question text"
    )
    val questionBoolean = Question(
        "",
        QuestionDifficulty.MEDIUM,
        "true",
        listOf("false"),
        QuestionType.BOOLEAN,
        "Question text"
    )
    val state = GetQuestionListState(
        isFetchingData = false,
        questions = listOf(
            question,
            questionBoolean,
            question
        ),
        error = null,
        amountOfQuestions = 3
    )
    MyApplicationTheme {
        GetQuestionListScreen(
            state = state,
            onEvent = {},
            modifier = Modifier.fillMaxSize(),
            snackbarHostState = SnackbarHostState()
        )
    }
}
