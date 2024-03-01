package com.klewerro.kmmquiz.presentation.question

import com.klewerro.kmmquiz.domain.model.question.QuestionCategory

sealed class GetQuestionListEvent {
    data object GetNewQuestionList : GetQuestionListEvent()
    data class ChangeAmount(val amountText: String) : GetQuestionListEvent()
    data object StartChoosingQuestionCategory : GetQuestionListEvent()
    data object StopChoosingQuestionCategory : GetQuestionListEvent()
    data class ChangeQuestionCategory(val questionCategory: QuestionCategory) : GetQuestionListEvent()
}
