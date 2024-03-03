package com.klewerro.kmmquiz.domain.model

enum class GetQuestionListError {
    SERVICE_UNAVAILABLE,
    CLIENT_ERROR,
    SERVER_ERROR,
    SERIALIZATION_ERROR,
    TOO_MANY_REQUESTS_ERROR,
    UNKNOWN_ERROR,
    AMOUNT_IS_0,
    QUESTION_ALREADY_SAVED
}
