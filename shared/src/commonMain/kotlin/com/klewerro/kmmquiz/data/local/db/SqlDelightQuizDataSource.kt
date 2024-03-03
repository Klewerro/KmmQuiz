package com.klewerro.kmmquiz.data.local.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.klewerro.kmmquiz.database.QuizDb
import com.klewerro.kmmquiz.domain.LocalDbDataSource
import com.klewerro.kmmquiz.domain.model.question.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock

class SqlDelightQuizDataSource(db: QuizDb) : LocalDbDataSource {
    private val commonQueries = db.commonQueries
    private val questionQueries = db.questionQueries

    override val questions = questionQueries.getQuestions()
        .asFlow()
        .mapToList(Dispatchers.IO)

    override suspend fun isQuestionWithTextAlreadySaved(question: Question): Boolean {
        return questionQueries.countOfQuestionsWithText(question.text).executeAsOne() > 0
    }

    override suspend fun insertQuestion(question: Question) = withContext(Dispatchers.IO) {
        questionQueries.insertQuestionEntity(
            id = null,
            category = question.category,
            difficulty = question.difficulty.ordinal.toLong(),
            correct_answer = question.correctAnswer,
            incorrect_answers = question.incorrectAnswers.joinToString(";"),
            type = question.type.ordinal.toLong(),
            text = question.text,
            time = Clock.System.now().toEpochMilliseconds()
        )
    }

    override suspend fun deleteQuestion(question: Question) = withContext(Dispatchers.IO) {
        questionQueries.deleteQuestionEntity(question.text)
    }
}
