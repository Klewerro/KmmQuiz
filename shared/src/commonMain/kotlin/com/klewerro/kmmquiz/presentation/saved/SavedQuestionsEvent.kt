package com.klewerro.kmmquiz.presentation.saved

import com.klewerro.kmmquiz.domain.model.question.Question

sealed class SavedQuestionsEvent {
    data class DeleteQuestion(val question: Question) : SavedQuestionsEvent()
    data class SelectableQuestionCheckedChanged(
        val selectableQuestion: SelectableSavedQuestion,
        val isChecked: Boolean
    ) : SavedQuestionsEvent()
}
