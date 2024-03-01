package com.klewerro.kmmquiz.presentation.question

import com.klewerro.kmmquiz.domain.usecase.GetQuestionListUseCase
import com.klewerro.kmmquiz.domain.util.flow.toCommonStateFlow
import com.klewerro.kmmquiz.presentation.CommonViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuestionViewModel(
    private val getQuestionListUseCase: GetQuestionListUseCase,
    private val coroutineScope: CoroutineScope?
) : CommonViewModel<QuestionState, QuestionEvent>(coroutineScope) {
    private val _state = MutableStateFlow(QuestionState())
    override val state =
        _state
            .asStateFlow()
            .toCommonStateFlow()

    override fun onEvent(event: QuestionEvent) {
//        when (event) {
//
//        }
    }
}
