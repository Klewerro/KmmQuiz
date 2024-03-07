package com.klewerro.kmmquiz.presentation.quiz

import com.klewerro.kmmquiz.domain.usecase.QuizListUseCase
import com.klewerro.kmmquiz.domain.util.flow.CommonStateFlow
import com.klewerro.kmmquiz.domain.util.flow.toCommonStateFlow
import com.klewerro.kmmquiz.presentation.CommonViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class QuizViewModel(
    quizListUseCase: QuizListUseCase,
    private val coroutineScope: CoroutineScope?
) : CommonViewModel<QuizState, QuizEvent>(coroutineScope) {

    private val _state = MutableStateFlow(QuizState())
    override val state: CommonStateFlow<QuizState> = _state
        .combine(quizListUseCase.execute()) { stateValue, quizList ->
            stateValue.copy(
                quizList = quizList
            )
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, QuizState())
        .toCommonStateFlow()

    override fun onEvent(event: QuizEvent) {
        when (event) {
            else -> Unit
        }
    }
}
