package com.klewerro.kmmquiz.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionDto(
    @SerialName("category")
    val category: String,
    @SerialName("correct_answer")
    val correctAnswer: String,
    @SerialName("difficulty")
    val difficulty: String,
    @SerialName("incorrect_answers")
    val incorrectAnswers: List<String>,
    @SerialName("question")
    val text: String,
    @SerialName("type")
    val type: String
)
