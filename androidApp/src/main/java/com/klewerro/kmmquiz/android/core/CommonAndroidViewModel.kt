package com.klewerro.kmmquiz.android.core

import androidx.lifecycle.ViewModel
import com.klewerro.kmmquiz.domain.util.flow.CommonStateFlow
import com.klewerro.kmmquiz.presentation.CommonViewModel

abstract class CommonAndroidViewModel<TState, TEvent> : ViewModel() {
    protected abstract val viewModel: CommonViewModel<TState, TEvent>
    abstract val state: CommonStateFlow<TState>

    fun onEvent(event: TEvent) = viewModel.onEvent(event)
}
