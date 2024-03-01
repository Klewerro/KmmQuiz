package com.klewerro.kmmquiz.presentation.question

sealed class GetQuestionListEvent {
    data object GetNewQuestionList : GetQuestionListEvent()
}
