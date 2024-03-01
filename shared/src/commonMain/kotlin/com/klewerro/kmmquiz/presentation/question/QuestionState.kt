package com.klewerro.kmmquiz.presentation.question

import com.klewerro.kmmquiz.data.error.QuestionApiError
import com.klewerro.kmmquiz.domain.model.question.Question

data class QuestionState(
    val isFetchingData: Boolean = false,
    val questions: List<Question> = emptyList(),
    val error: QuestionApiError? = null
)
