package com.klewerro.kmmquiz.domain

import kotlinx.coroutines.flow.Flow

interface KeyValueStorage {
    var amount: Int?
    var questionCategory: Int?

    val amountFlow: Flow<Int>
    val questionCategoryIdFlow: Flow<Int>

    fun cleanStorage()
}
