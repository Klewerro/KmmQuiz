package com.klewerro.kmmquiz.data.local.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.klewerro.kmmquiz.database.QuizDb
import com.klewerro.kmmquiz.domain.LocalDbDataSource
import com.klewerro.kmmquiz.domain.model.question.Question
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock

class SqlDelightQuizDataSource(db: QuizDb) : LocalDbDataSource {
    private val commonQueries = db.commonQueries
    private val questionQueries = db.questionQueries
    private val quizQueries = db.quizQueries

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

    override suspend fun insertQuiz(title: String, questions: List<Question>) =
        suspendCoroutine<Boolean> { continuation ->
            quizQueries.transaction {
                afterRollback {
                    continuation.resume(false)
                }
                afterCommit {
                    continuation.resume(true)
                }

                quizQueries.insertQuizEntity(
                    id = null,
                    title = title,
                    time = Clock.System.now().toEpochMilliseconds()
                )
                val quizId = commonQueries.lastInsertRowId().executeAsOne()
                questions.forEach { question ->
                    questionQueries.updateQuestionQuizId(quizId, question.text)
                }
            }
        }

    override suspend fun deleteQuestion(question: Question) = withContext(Dispatchers.IO) {
        questionQueries.deleteQuestionEntity(question.text)
    }
}
