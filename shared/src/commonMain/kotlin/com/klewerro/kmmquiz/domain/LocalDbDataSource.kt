package com.klewerro.kmmquiz.domain

import com.klewerro.kmmquiz.domain.model.question.Question
import database.QuestionEntity
import kotlinx.coroutines.flow.Flow

interface LocalDbDataSource {
    val questions: Flow<List<QuestionEntity>>
    suspend fun insertQuestion(question: Question)
    suspend fun isQuestionWithTextAlreadySaved(question: Question): Boolean
    suspend fun deleteQuestion(question: Question)
}
