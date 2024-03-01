package com.klewerro.kmmquiz.presentation.question

import com.klewerro.kmmquiz.data.error.QuestionApiException
import com.klewerro.kmmquiz.domain.usecase.GetQuestionListUseCase
import com.klewerro.kmmquiz.domain.util.Resource
import com.klewerro.kmmquiz.domain.util.flow.toCommonStateFlow
import com.klewerro.kmmquiz.presentation.CommonViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GetQuestionListViewModel(
    private val getQuestionListUseCase: GetQuestionListUseCase,
    private val coroutineScope: CoroutineScope?
) : CommonViewModel<GetQuestionState, GetQuestionEvent>(coroutineScope) {
    private var getQuestionListJob: Job? = null
    private val _state = MutableStateFlow(GetQuestionState())
    override val state = _state
        .asStateFlow()
        .toCommonStateFlow()

    override fun onEvent(event: GetQuestionEvent) {
        when (event) {
            GetQuestionEvent.GetNewQuestion -> {
                if (state.value.isFetchingData) {
                    return
                }

                getQuestionListJob = viewModelScope.launch(Dispatchers.IO) {
                    _state.update {
                        it.copy(
                            isFetchingData = true
                        )
                    }
                    val category = 1
                    val amount = 3
                    when (val getQuestionsResult = getQuestionListUseCase.execute(category, amount)) {
                        is Resource.Success -> _state.update {
                            it.copy(
                                isFetchingData = false,
                                questions = getQuestionsResult.data ?: it.questions
                            )
                        }
                        is Resource.Error -> _state.update {
                            it.copy(
                                isFetchingData = false,
                                error = (getQuestionsResult.throwable as? QuestionApiException)?.error
                            )
                        }
                    }
                }
            }
        }
    }
}
