package com.klewerro.kmmquiz.android.question

import androidx.lifecycle.viewModelScope
import com.klewerro.kmmquiz.android.core.CommonAndroidViewModel
import com.klewerro.kmmquiz.domain.usecase.GetQuestionListUseCase
import com.klewerro.kmmquiz.presentation.question.GetQuestionListEvent
import com.klewerro.kmmquiz.presentation.question.GetQuestionListViewModel
import com.klewerro.kmmquiz.presentation.question.GetQuestionListState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetQuestionListAndroidViewModel @Inject constructor(
    private val getQuestionListUseCase: GetQuestionListUseCase
) : CommonAndroidViewModel<GetQuestionListState, GetQuestionListEvent>() {
    override val viewModel by lazy {
        GetQuestionListViewModel(
            getQuestionListUseCase = getQuestionListUseCase,
            coroutineScope = viewModelScope
        )
    }

    override val state = viewModel.state
}
