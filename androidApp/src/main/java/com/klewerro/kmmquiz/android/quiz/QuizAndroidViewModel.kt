package com.klewerro.kmmquiz.android.quiz

import androidx.lifecycle.viewModelScope
import com.klewerro.kmmquiz.android.core.CommonAndroidViewModel
import com.klewerro.kmmquiz.domain.usecase.QuizListUseCase
import com.klewerro.kmmquiz.presentation.quiz.QuizEvent
import com.klewerro.kmmquiz.presentation.quiz.QuizState
import com.klewerro.kmmquiz.presentation.quiz.QuizViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizAndroidViewModel @Inject constructor(
    quizListUseCase: QuizListUseCase
) : CommonAndroidViewModel<QuizState, QuizEvent>() {
    override val viewModel by lazy {
        QuizViewModel(
            quizListUseCase = quizListUseCase,
            coroutineScope = viewModelScope
        )
    }
    override val state = viewModel.state
}
