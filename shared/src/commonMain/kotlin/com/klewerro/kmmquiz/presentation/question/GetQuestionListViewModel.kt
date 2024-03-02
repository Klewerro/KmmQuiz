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
) : CommonViewModel<GetQuestionListState, GetQuestionListEvent>(coroutineScope) {
    private var getQuestionListJob: Job? = null
    private val _state = MutableStateFlow(GetQuestionListState())
    override val state = _state
        .asStateFlow()
        .toCommonStateFlow()

    override fun onEvent(event: GetQuestionListEvent) {
        when (event) {
            GetQuestionListEvent.GetNewQuestionList -> {
                if (state.value.isFetchingData) {
                    return
                }

                getQuestionListJob = viewModelScope.launch(Dispatchers.IO) {
                    _state.update {
                        it.copy(
                            isFetchingData = true
                        )
                    }

                    val category = state.value.questionCategory
                    val amount = state.value.amountOfQuestions
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

            is GetQuestionListEvent.ChangeAmount -> {
                val amountInt = try {
                    event.amountText.toInt()
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                    -1
                }
                _state.update {
                    it.copy(
                        amountOfQuestions = amountInt
                    )
                }
            }

            GetQuestionListEvent.StartChoosingQuestionCategory -> _state.update {
                it.copy(
                    isChoosingQuestionCategory = true
                )
            }
            GetQuestionListEvent.StopChoosingQuestionCategory -> _state.update {
                it.copy(
                    isChoosingQuestionCategory = false
                )
            }
            is GetQuestionListEvent.ChangeQuestionCategory -> _state.update {
                it.copy(
                    isChoosingQuestionCategory = false,
                    questionCategory = event.questionCategory
                )
            }
        }
    }
}
