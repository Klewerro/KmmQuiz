package com.klewerro.kmmquiz.presentation.question

sealed class GetQuestionEvent {
    data object GetNewQuestion : GetQuestionEvent()
}
