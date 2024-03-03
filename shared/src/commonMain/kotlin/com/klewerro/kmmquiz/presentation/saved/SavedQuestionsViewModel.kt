package com.klewerro.kmmquiz.presentation.saved

import com.klewerro.kmmquiz.domain.model.question.Question
import com.klewerro.kmmquiz.domain.usecase.SavedQuestionsUseCase
import com.klewerro.kmmquiz.domain.util.flow.toCommonStateFlow
import com.klewerro.kmmquiz.presentation.CommonViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SavedQuestionsViewModel(
    savedQuestionsUseCase: SavedQuestionsUseCase,
    private val coroutineScope: CoroutineScope?
) : CommonViewModel<SavedQuestionsState, SavedQuestionsEvent>(coroutineScope) {

    private val isQuestionSelectedMap = MutableStateFlow<Map<Question, Boolean>>(emptyMap())
    private val _state = MutableStateFlow(SavedQuestionsState())

    override val state = combine(
        _state,
        savedQuestionsUseCase.execute(),
        isQuestionSelectedMap
    ) { stateValue, savedQuestions, isSelectedMap ->
        stateValue.copy(
            savedQuestions = savedQuestions.map { question ->
                SelectableSavedQuestion(
                    question,
                    isSelectedMap[question] ?: false
                )
            }
        )
    }
        .stateIn(viewModelScope, SharingStarted.Eagerly, SavedQuestionsState())
        .toCommonStateFlow()

    override fun onEvent(event: SavedQuestionsEvent) {
        when (event) {
            is SavedQuestionsEvent.DeleteQuestion -> TODO()
            is SavedQuestionsEvent.SelectableQuestionCheckedChanged -> {
                isQuestionSelectedMap.update {
                    isQuestionSelectedMap.value.toMutableMap().apply {
                        this[event.selectableQuestion.question] = event.isChecked
                    }.toMap()
                }
            }
        }
    }
}
