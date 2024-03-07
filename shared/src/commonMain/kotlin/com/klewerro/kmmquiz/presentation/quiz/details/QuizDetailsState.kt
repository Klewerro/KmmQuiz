package com.klewerro.kmmquiz.presentation.quiz.details

import com.klewerro.kmmquiz.domain.model.Quiz
import com.klewerro.kmmquiz.domain.model.question.QuestionAnswer

data class QuizDetailsState(
    val quiz: Quiz? = null,
    val answers: Map<Int, QuestionAnswer> = emptyMap(),
    val getQuizError: Boolean? = false
) {
    fun areAllAnswersSelected() = quiz?.questions?.size == answers.values.size
}
