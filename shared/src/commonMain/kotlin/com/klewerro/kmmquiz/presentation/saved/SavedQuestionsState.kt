package com.klewerro.kmmquiz.presentation.saved

data class SavedQuestionsState(
    val savedQuestions: List<SelectableSavedQuestion> = emptyList(),
    val newQuizName: String = "",
    val isSelectionModeEnabled: Boolean = savedQuestions.any { it.isSelected }
)
