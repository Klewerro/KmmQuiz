package com.klewerro.kmmquiz.presentation.saved

import com.klewerro.kmmquiz.domain.model.question.Question

data class SavedQuestionsState(
    val savedQuestions: List<Question> = emptyList(),
    val isSelectionModeEnabled: Boolean = false,
    val newQuizName: String = ""
)
