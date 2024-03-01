package com.klewerro.kmmquiz.domain

import com.klewerro.kmmquiz.domain.model.question.Question

interface QuestionClient {
    suspend fun getQuestions(questionCategoryId: Int, amount: Int): List<Question>
}
