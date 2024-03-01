package com.klewerro.kmmquiz.android.question

import androidx.lifecycle.viewModelScope
import com.klewerro.kmmquiz.android.core.CommonAndroidViewModel
import com.klewerro.kmmquiz.domain.usecase.GetQuestionListUseCase
import com.klewerro.kmmquiz.presentation.question.GetQuestionEvent
import com.klewerro.kmmquiz.presentation.question.GetQuestionState
import com.klewerro.kmmquiz.presentation.question.GetQuestionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetQuestionListAndroidViewModel @Inject constructor(
    private val getQuestionListUseCase: GetQuestionListUseCase
) : CommonAndroidViewModel<GetQuestionState, GetQuestionEvent>() {
    override val viewModel by lazy {
        GetQuestionViewModel(
            getQuestionListUseCase = getQuestionListUseCase,
            coroutineScope = viewModelScope
        )
    }

    override val state = viewModel.state
}
