package com.klewerro.kmmquiz.presentation.quiz.details

import com.klewerro.kmmquiz.domain.LocalDbDataSource
import com.klewerro.kmmquiz.domain.model.question.QuestionAnswer
import com.klewerro.kmmquiz.domain.util.flow.toCommonStateFlow
import com.klewerro.kmmquiz.presentation.CommonViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuizDetailsViewModel(
    private val quizId: Long,
    private val localDbDataSource: LocalDbDataSource,
    private val coroutineScope: CoroutineScope?
) : CommonViewModel<QuizDetailsState, QuizDetailsEvent>(
    coroutineScope
) {
    private val answers = MutableStateFlow<Map<Int, QuestionAnswer>>(emptyMap())

    private val _state = MutableStateFlow(QuizDetailsState())

    init {
        getQuiz()
    }

    override val state = _state
        .combine(answers) { stateValue, answers ->
            stateValue.copy(
                answers = answers
            )
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, QuizDetailsState())
        .toCommonStateFlow()

    override fun onEvent(event: QuizDetailsEvent) {
        when (event) {
            QuizDetailsEvent.OnErrorDismissed -> _state.update {
                it.copy(
                    getQuizError = null
                )
            }

            is QuizDetailsEvent.QuestionAnswered -> {
                handleQuestionAnswered(
                    questionIndex = event.questionIndex,
                    answerIndex = event.answerIndex
                )
            }

            QuizDetailsEvent.SaveAnswers -> {
                state.value.quiz?.let { quizValue ->
                    viewModelScope.launch(Dispatchers.IO) {
                        localDbDataSource.insertQuestionAnswers(
                            quizId = quizValue.id,
                            questionAnswers = state.value.answers.values.toList()
                        )
                    }
                }
            }
        }
    }

    private fun getQuiz() {
        if (quizId == -1L) {
            setGetQuizErrorState()
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                localDbDataSource.getQuiz(quizId)?.let { quiz ->
                    _state.update {
                        it.copy(
                            quiz = quiz
                        )
                    }
                } ?: run {
                    setGetQuizErrorState()
                }
            }
        }
    }

    private fun setGetQuizErrorState() {
        _state.update {
            it.copy(
                getQuizError = true
            )
        }
    }

    private fun handleQuestionAnswered(questionIndex: Int, answerIndex: Int) {
        state.value.quiz?.questions?.let { questionsValue ->
            val question = questionsValue[questionIndex]
            val correctAnswer = question.correctAnswer
            val selectedAnswer = question.allAnswers[answerIndex]
            val questionAnswer = QuestionAnswer(
                question,
                selectedAnswer,
                correctAnswer
            )
            answers.update {
                it.toMutableMap().apply {
                    this[questionIndex] = questionAnswer
                }.toMap()
            }
        } ?: run {
            setGetQuizErrorState()
        }
    }
}
