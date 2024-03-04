package com.klewerro.kmmquiz.presentation.saved

data class SavedQuestionsState(
    val savedQuestions: List<SelectableSavedQuestion> = emptyList(),
    val newQuizName: String = "",
    val saveQuizResult: Boolean? = null
) {
    fun isAnyQuestionSelected() = savedQuestions.any { it.isSelected }
    fun selectedQuestionsCount() = savedQuestions.count { it.isSelected }
}
