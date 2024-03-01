package com.klewerro.kmmquiz.domain.usecase

import com.klewerro.kmmquiz.domain.QuestionClient
import com.klewerro.kmmquiz.domain.model.question.Question
import com.klewerro.kmmquiz.domain.util.Resource

class GetQuestionListUseCase(private val questionClient: QuestionClient) {
    suspend fun execute(
        category: Int,
        amount: Int
    ): Resource<List<Question>> {
        return try {
            val questions = questionClient.getQuestions(category, amount)
            Resource.Success(questions)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }
}
