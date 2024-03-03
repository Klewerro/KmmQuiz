package com.klewerro.kmmquiz.presentation.saved

import com.klewerro.kmmquiz.domain.util.flow.CommonStateFlow
import com.klewerro.kmmquiz.domain.util.flow.toCommonStateFlow
import com.klewerro.kmmquiz.presentation.CommonViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SavedQuestionsViewModel(
    private val coroutineScope: CoroutineScope?
) : CommonViewModel<SavedQuestionsState, SavedQuestionsEvent>(coroutineScope) {
    private val _state = MutableStateFlow(SavedQuestionsState())
    override val state: CommonStateFlow<SavedQuestionsState> = _state
        .asStateFlow()
        .toCommonStateFlow()

    override fun onEvent(event: SavedQuestionsEvent) {
        when (event) {
            else -> TODO()
        }
    }
}
