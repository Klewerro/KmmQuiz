package com.klewerro.kmmquiz.domain.mapper

import com.klewerro.kmmquiz.data.dto.QuestionDto
import com.klewerro.kmmquiz.domain.model.question.Question
import com.klewerro.kmmquiz.domain.model.question.QuestionDifficulty
import com.klewerro.kmmquiz.domain.model.question.QuestionType

object QuestionMapper {
    fun map(questionDto: QuestionDto): Question {
        return Question(
            category = questionDto.category,
            difficulty = mapDifficultyText(questionDto.difficulty),
            correctAnswer = questionDto.correctAnswer,
            incorrectAnswers = questionDto.incorrectAnswers,
            type = mapQuestionTypeText(questionDto.type),
            text = questionDto.text
        )
    }

    private fun mapDifficultyText(difficultyText: String): QuestionDifficulty {
        return when (difficultyText) {
            "easy" -> QuestionDifficulty.EASY
            "medium" -> QuestionDifficulty.MEDIUM
            "hard" -> QuestionDifficulty.HARD
            else -> throw IllegalArgumentException()
        }
    }

    private fun mapQuestionTypeText(questionTypeText: String): QuestionType {
        return when (questionTypeText) {
            "boolean" -> QuestionType.BOOLEAN
            "multiple" -> QuestionType.MULTIPLE
            else -> throw IllegalArgumentException()
        }
    }
}
