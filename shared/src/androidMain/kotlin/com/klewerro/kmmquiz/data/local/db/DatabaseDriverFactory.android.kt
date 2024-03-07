package com.klewerro.kmmquiz.data.local.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.klewerro.kmmquiz.database.QuizDb

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            schema = QuizDb.Schema,
            context = context,
            name = "trivia.db"
        )
    }
}
