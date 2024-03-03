package com.klewerro.kmmquiz.android.saved

import androidx.lifecycle.viewModelScope
import com.klewerro.kmmquiz.android.core.CommonAndroidViewModel
import com.klewerro.kmmquiz.domain.LocalDbDataSource
import com.klewerro.kmmquiz.domain.usecase.SavedQuestionsUseCase
import com.klewerro.kmmquiz.presentation.saved.SavedQuestionsEvent
import com.klewerro.kmmquiz.presentation.saved.SavedQuestionsState
import com.klewerro.kmmquiz.presentation.saved.SavedQuestionsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedQuestionsAndroidViewModel @Inject constructor(
    savedQuestionsUseCase: SavedQuestionsUseCase,
    localDbDataSource: LocalDbDataSource
) : CommonAndroidViewModel<SavedQuestionsState, SavedQuestionsEvent>() {
    override val viewModel by lazy {
        SavedQuestionsViewModel(
            savedQuestionsUseCase = savedQuestionsUseCase,
            localDbDataSource = localDbDataSource,
            coroutineScope = viewModelScope
        )
    }
    override val state = viewModel.state
}
