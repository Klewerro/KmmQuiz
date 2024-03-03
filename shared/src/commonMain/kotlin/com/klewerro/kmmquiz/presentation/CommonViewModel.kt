package com.klewerro.kmmquiz.presentation

import com.klewerro.kmmquiz.domain.util.flow.CommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class CommonViewModel<TState, TEvent>(private val coroutineScope: CoroutineScope?) {
    protected val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    abstract val state: CommonStateFlow<TState>

    abstract fun onEvent(event: TEvent)
}
