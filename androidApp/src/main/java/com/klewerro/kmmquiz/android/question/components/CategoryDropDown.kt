package com.klewerro.kmmquiz.android.question.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.klewerro.kmmquiz.android.MyApplicationTheme
import com.klewerro.kmmquiz.domain.model.question.QuestionCategory

@Composable
fun CategoryDropDown(
    currentQuestionCategory: QuestionCategory,
    isOpen: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onCategoryClick: (QuestionCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        DropdownMenu(
            expanded = isOpen,
            onDismissRequest = onDismiss
        ) {
            QuestionCategory.entries.forEach { questionCategory ->
                DropdownMenuItem(
                    text = { Text(questionCategory.formattedName) },
                    onClick = {
                        onCategoryClick(questionCategory)
                    }
                )
            }
        }
        Text(
            text = currentQuestionCategory.formattedName,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(TextFieldDefaults.MinHeight)
                .clip(TextFieldDefaults.shape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .clickable {
                    onClick()
                }
                .wrapContentHeight(Alignment.CenterVertically)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryDropDownPreview() {
    MyApplicationTheme {
        CategoryDropDown(
            currentQuestionCategory = QuestionCategory.GENERAL_KNOWLEDGE,
            isOpen = false,
            onClick = {},
            onDismiss = {},
            onCategoryClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryDropDownOpenedPreview() {
    MyApplicationTheme {
        CategoryDropDown(
            currentQuestionCategory = QuestionCategory.GENERAL_KNOWLEDGE,
            isOpen = true,
            onClick = {},
            onDismiss = {},
            onCategoryClick = {}
        )
    }
}
