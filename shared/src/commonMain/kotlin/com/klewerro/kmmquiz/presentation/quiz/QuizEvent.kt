package com.klewerro.kmmquiz.presentation.quiz

sealed class QuizEvent {
    data class GoToQuestions(val quizId: Long) : QuizEvent()
}
