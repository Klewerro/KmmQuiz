package com.klewerro.kmmquiz.android.question

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.klewerro.kmmquiz.SharedRes
import com.klewerro.kmmquiz.android.MyApplicationTheme
import com.klewerro.kmmquiz.android.core.sharedStringResource
import com.klewerro.kmmquiz.android.question.components.CategoryDropDown
import com.klewerro.kmmquiz.android.question.components.ProgressButton
import com.klewerro.kmmquiz.presentation.question.GetQuestionListEvent
import com.klewerro.kmmquiz.presentation.question.GetQuestionListState

@Composable
fun GetQuestionListScreen(
    state: GetQuestionListState,
    onEvent: (GetQuestionListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
            onClick = { onEvent(GetQuestionListEvent.GetNewQuestionList) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    val state = GetQuestionListState(
        false,
        emptyList(),
        null,
        3
    )
    MyApplicationTheme {
        GetQuestionListScreen(state = state, onEvent = {}, modifier = Modifier.fillMaxSize())
    }
}
