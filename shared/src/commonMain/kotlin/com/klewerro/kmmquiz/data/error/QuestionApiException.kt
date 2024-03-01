package com.klewerro.kmmquiz.data.error

class QuestionApiException(val error: QuestionApiError) : Exception(
    "An error occurred during request: $error"
)
