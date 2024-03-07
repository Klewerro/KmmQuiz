package com.klewerro.kmmquiz.domain

import com.klewerro.kmmquiz.domain.model.Quiz
import com.klewerro.kmmquiz.domain.model.question.Question
import com.klewerro.kmmquiz.domain.model.question.QuestionAnswer
import database.QuestionEntity
import database.QuizEntity
import kotlinx.coroutines.flow.Flow

interface LocalDbDataSource {
    val questions: Flow<List<QuestionEntity>>
    suspend fun insertQuestion(question: Question)
    suspend fun isQuestionWithTextAlreadySaved(question: Question): Boolean
    suspend fun deleteQuestion(question: Question)
    suspend fun insertQuiz(title: String, questions: List<Question>): Boolean
    val quizList: Flow<List<QuizEntity>>
    suspend fun getQuizQuestions(quizId: Long): List<Question>
    suspend fun getQuiz(quizId: Long): Quiz?
    suspend fun insertQuestionAnswers(quizId: Long, questionAnswers: List<QuestionAnswer>): Boolean
}
