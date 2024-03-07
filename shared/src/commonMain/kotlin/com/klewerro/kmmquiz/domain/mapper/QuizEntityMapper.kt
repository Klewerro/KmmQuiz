package com.klewerro.kmmquiz.domain.mapper

import com.klewerro.kmmquiz.domain.model.Quiz
import com.klewerro.kmmquiz.domain.model.question.Question
import database.QuizEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object QuizEntityMapper {
    fun QuizEntity.mapToQuiz(questions: List<Question>): Quiz {
        val instant = Instant.fromEpochMilliseconds(this.time)
        val time = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return Quiz(
            id = this.id,
            title = this.title,
            time = time,
            questions = questions
        )
    }
}
