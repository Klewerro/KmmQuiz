package com.klewerro.kmmquiz.domain.model.question

data class QuestionAnswer(
    val question: Question,
    val answer: String,
    val correctAnswer: String
) {
    fun isAnswerCorrect() = correctAnswer == answer
}
