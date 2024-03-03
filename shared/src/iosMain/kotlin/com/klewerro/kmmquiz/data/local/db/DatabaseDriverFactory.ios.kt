package com.klewerro.kmmquiz.data.local.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.klewerro.kmmquiz.database.QuizDb

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(
            schema = QuizDb.Schema,
            name = "translate.db"
        )
    }
}
