package com.klewerro.kmmquiz.presentation.quiz.details

sealed class QuizDetailsEvent {
    data object OnErrorDismissed : QuizDetailsEvent()
    data class QuestionAnswered(val questionIndex: Int, val answerIndex: Int) : QuizDetailsEvent()
    data object SaveAnswers : QuizDetailsEvent()
}
