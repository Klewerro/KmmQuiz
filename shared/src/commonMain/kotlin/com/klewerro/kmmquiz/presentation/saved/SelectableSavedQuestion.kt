package com.klewerro.kmmquiz.presentation.saved

import com.klewerro.kmmquiz.domain.model.question.Question

data class SelectableSavedQuestion(
    val question: Question,
    val isSelected: Boolean
)
