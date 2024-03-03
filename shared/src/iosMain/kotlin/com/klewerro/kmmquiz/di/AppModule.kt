package com.klewerro.kmmquiz.di

import com.klewerro.kmmquiz.data.local.db.DatabaseDriverFactory
import com.klewerro.kmmquiz.data.local.db.SqlDelightQuizDataSource
import com.klewerro.kmmquiz.data.local.keyvalue.SharedKeyValueStorage
import com.klewerro.kmmquiz.data.remote.HttpClientFactory
import com.klewerro.kmmquiz.data.remote.KtorQuestionClient
import com.klewerro.kmmquiz.database.QuizDb
import com.klewerro.kmmquiz.domain.LocalDbDataSource
import com.klewerro.kmmquiz.domain.QuestionClient
import com.klewerro.kmmquiz.domain.usecase.GetQuestionListUseCase

class AppModule {

    private val questionClient: QuestionClient by lazy {
        KtorQuestionClient(
            HttpClientFactory().create()
        )
    }

    val getQuestionListUseCase by lazy {
        GetQuestionListUseCase(questionClient)
    }

    val keyValueStorage by lazy {
        SharedKeyValueStorage()
    }

    val localDbDataSource: LocalDbDataSource by lazy {
        SqlDelightQuizDataSource(
            QuizDb(
                DatabaseDriverFactory().create()
            )
        )
    }
}
