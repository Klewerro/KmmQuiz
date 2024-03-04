package com.klewerro.kmmquiz.domain.model

import com.klewerro.kmmquiz.domain.model.question.Question
import kotlinx.datetime.LocalDateTime

data class Quiz(
    val id: Long,
    val title: String,
    val time: LocalDateTime,
    val questions: List<Question>
)
