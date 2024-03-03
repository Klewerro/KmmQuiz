package com.klewerro.kmmquiz.presentation.saved

import com.klewerro.kmmquiz.domain.model.question.Question

sealed class SavedQuestionsEvent {
    data class DeleteQuestion(val question: Question) : SavedQuestionsEvent()
    data class QuestionSelectionChanged(
        val selectableQuestion: SelectableSavedQuestion,
        val isSelected: Boolean
    ) : SavedQuestionsEvent()

    data class ChangeQuizNameText(val nameText: String) : SavedQuestionsEvent()
    data object SaveQuiz : SavedQuestionsEvent()
    data object OnQuizCreateMessageSeen : SavedQuestionsEvent()
}
