package com.klewerro.kmmquiz.android.quiz.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.klewerro.kmmquiz.SharedRes
import com.klewerro.kmmquiz.android.core.sharedStringResource
import com.klewerro.kmmquiz.domain.model.Quiz

@Composable
fun QuizListItem(
    quiz: Quiz,
    onRootClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable {
                onRootClick()
            }
            .padding(8.dp)
    ) {
        Text(text = quiz.title)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(sharedStringResource(SharedRes.strings.questions_size, quiz.questions.size))
            Text(sharedStringResource(SharedRes.strings.save_time, quiz.getFormattedTime()))
        }
    }
}
