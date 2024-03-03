package com.klewerro.kmmquiz.android.saved.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SaveAs
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.klewerro.kmmquiz.SharedRes
import com.klewerro.kmmquiz.android.core.sharedStringResource

@Composable
fun SaveQuizHeader(
    isVisible: Boolean,
    textValue: String,
    onTextChange: (String) -> Unit,
    onSaveButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = expandVertically(),
        exit = shrinkVertically(),
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = textValue,
                label = {
                    Text(sharedStringResource(SharedRes.strings.quiz_name))
                },
                modifier = Modifier.weight(1f),
                onValueChange = onTextChange
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                modifier = Modifier
                    .size(50.dp),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                onClick = onSaveButtonClick
            ) {
                Icon(
                    imageVector = Icons.Outlined.SaveAs,
                    contentDescription = sharedStringResource(SharedRes.strings.content_description_save_quiz)
                )
            }
        }
    }
}
