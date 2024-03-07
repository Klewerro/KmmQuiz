package com.klewerro.kmmquiz.android.quiz.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.klewerro.kmmquiz.android.core.CommonAndroidViewModel
import com.klewerro.kmmquiz.domain.LocalDbDataSource
import com.klewerro.kmmquiz.presentation.quiz.details.QuizDetailsEvent
import com.klewerro.kmmquiz.presentation.quiz.details.QuizDetailsState
import com.klewerro.kmmquiz.presentation.quiz.details.QuizDetailsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizDetailsAndroidViewModel @Inject constructor(
    savedState: SavedStateHandle,
    localDbDataSource: LocalDbDataSource
) : CommonAndroidViewModel<QuizDetailsState, QuizDetailsEvent>() {
    override val viewModel by lazy {
        QuizDetailsViewModel(
            savedState.get<Long>("quiz_id") ?: -1,
            localDbDataSource,
            viewModelScope
        )
    }

    override val state = viewModel.state
}
