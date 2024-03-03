package com.klewerro.kmmquiz.presentation.question

import com.klewerro.kmmquiz.data.remote.QuestionApiException
import com.klewerro.kmmquiz.domain.KeyValueStorage
import com.klewerro.kmmquiz.domain.model.GetQuestionListError
import com.klewerro.kmmquiz.domain.model.question.QuestionCategory
import com.klewerro.kmmquiz.domain.usecase.GetQuestionListUseCase
import com.klewerro.kmmquiz.domain.util.Resource
import com.klewerro.kmmquiz.domain.util.flow.toCommonStateFlow
import com.klewerro.kmmquiz.presentation.CommonViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GetQuestionListViewModel(
    private val getQuestionListUseCase: GetQuestionListUseCase,
    private val keyValueStorage: KeyValueStorage,
    private val coroutineScope: CoroutineScope?
) : CommonViewModel<GetQuestionListState, GetQuestionListEvent>(coroutineScope) {
    private var getQuestionListJob: Job? = null
    private val _state = MutableStateFlow(GetQuestionListState())
    override val state = combine(
        _state,
        keyValueStorage.amountFlow,
        keyValueStorage.questionCategoryIdFlow
    ) { stateValue, amount, questionCategoryId ->
        stateValue.copy(
            amountOfQuestions = amount,
            questionCategory = if (questionCategoryId > -1) {
                QuestionCategory.fromId(questionCategoryId)
            } else {
                stateValue.questionCategory
            }
        )
    }
        .stateIn(viewModelScope, SharingStarted.Eagerly, GetQuestionListState())
        .toCommonStateFlow()

    override fun onEvent(event: GetQuestionListEvent) {
        when (event) {
            GetQuestionListEvent.GetNewQuestionList -> {
                if (state.value.isFetchingData) {
                    return
                }

                if (state.value.amountOfQuestions < 1) {
                    _state.update {
                        it.copy(
                            error = GetQuestionListError.AMOUNT_IS_0
                        )
                    }
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
                keyValueStorage.amount = amountInt
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
                keyValueStorage.questionCategory = event.questionCategory.id
                it.copy(
                    isChoosingQuestionCategory = false
                )
            }

            GetQuestionListEvent.OnErrorSeen -> {
                _state.update {
                    it.copy(
                        error = null
                    )
                }
            }
        }
    }
}
