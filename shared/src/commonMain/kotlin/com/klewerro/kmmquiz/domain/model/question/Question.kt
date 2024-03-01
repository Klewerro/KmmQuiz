package com.klewerro.kmmquiz.domain.model.question

data class Question(
    val category: String,
    val difficulty: QuestionDifficulty,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
    val type: QuestionType,
    val text: String,
    val allAnswers: List<String> = incorrectAnswers.plus(correctAnswer)
)
