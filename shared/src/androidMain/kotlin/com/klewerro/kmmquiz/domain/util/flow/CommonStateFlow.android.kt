package com.klewerro.kmmquiz.domain.util.flow

import kotlinx.coroutines.flow.StateFlow

actual class CommonStateFlow<T> actual constructor(private val flow: StateFlow<T>) :
    StateFlow<T> by flow
