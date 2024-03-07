package com.klewerro.kmmquiz.data.remote

import com.klewerro.kmmquiz.domain.model.GetQuestionListError

class QuestionApiException(val error: GetQuestionListError) : Exception(
    "An error occurred during request: $error"
)
