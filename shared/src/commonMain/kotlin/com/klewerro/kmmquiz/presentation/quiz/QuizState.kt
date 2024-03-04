package com.klewerro.kmmquiz.presentation.quiz

import com.klewerro.kmmquiz.domain.model.Quiz

data class QuizState(
    val quizList: List<Quiz> = emptyList()
)
