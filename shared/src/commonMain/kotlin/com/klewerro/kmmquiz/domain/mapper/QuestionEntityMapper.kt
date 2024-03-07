package com.klewerro.kmmquiz.domain.mapper

import com.klewerro.kmmquiz.domain.model.question.Question
import com.klewerro.kmmquiz.domain.model.question.QuestionDifficulty
import com.klewerro.kmmquiz.domain.model.question.QuestionType
import database.QuestionEntity

object QuestionEntityMapper {

    fun QuestionEntity.mapToQuestion(): Question {
        return Question(
            category = this.category,
            difficulty = QuestionDifficulty.entries[this.difficulty.toInt()],
            correctAnswer = this.correct_answer,
            incorrectAnswers = this.incorrect_answers.split(";"),
            type = QuestionType.entries[this.type.toInt()],
            text = this.text
        )
    }
}
