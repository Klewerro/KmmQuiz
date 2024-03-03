package com.klewerro.kmmquiz.presentation.saved

import com.klewerro.kmmquiz.domain.usecase.SavedQuestionsUseCase
import com.klewerro.kmmquiz.domain.util.flow.toCommonStateFlow
import com.klewerro.kmmquiz.presentation.CommonViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class SavedQuestionsViewModel(
    savedQuestionsUseCase: SavedQuestionsUseCase,
    private val coroutineScope: CoroutineScope?
) : CommonViewModel<SavedQuestionsState, SavedQuestionsEvent>(coroutineScope) {
    private val _state = MutableStateFlow(SavedQuestionsState())
    override val state = _state.combine(savedQuestionsUseCase.execute()) { stateValue, savedQuestions ->
        stateValue.copy(
            savedQuestions = savedQuestions
        )
    }
        .stateIn(viewModelScope, SharingStarted.Eagerly, SavedQuestionsState())
        .toCommonStateFlow()

    override fun onEvent(event: SavedQuestionsEvent) {
        when (event) {
            else -> TODO()
        }
    }
}
