package com.klewerro.kmmquiz.domain.util.flow

import kotlinx.coroutines.flow.MutableStateFlow

expect class CommonMutableStateFlow<T>(flow: MutableStateFlow<T>) : MutableStateFlow<T>

fun <T> MutableStateFlow<T>.toCommonStateFlow() = CommonStateFlow(this)
