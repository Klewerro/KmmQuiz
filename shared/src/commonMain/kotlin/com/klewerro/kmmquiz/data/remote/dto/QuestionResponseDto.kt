package com.klewerro.kmmquiz.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionResponseDto(
    @SerialName("response_code")
    val responseCode: Int,
    @SerialName("results")
    val questionDtoList: List<QuestionDto>
)
