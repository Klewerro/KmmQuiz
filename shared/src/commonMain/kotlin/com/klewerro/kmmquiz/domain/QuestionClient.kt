package com.klewerro.kmmquiz.domain

import com.klewerro.kmmquiz.domain.model.question.Question
import com.klewerro.kmmquiz.domain.model.question.QuestionCategory

interface QuestionClient {
    suspend fun getQuestions(questionCategory: QuestionCategory, amount: Int): List<Question>
}
