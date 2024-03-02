package com.klewerro.kmmquiz.presentation.question

import com.klewerro.kmmquiz.data.remote.error.QuestionApiError
import com.klewerro.kmmquiz.domain.model.question.Question
import com.klewerro.kmmquiz.domain.model.question.QuestionCategory

data class GetQuestionListState(
    val isFetchingData: Boolean = false,
    val questions: List<Question> = emptyList(),
    val error: QuestionApiError? = null,
    val amountOfQuestions: Int = DEFAULT_QUESTION_AMOUNT,
    val questionCategory: QuestionCategory = QuestionCategory.GENERAL_KNOWLEDGE,
    val isChoosingQuestionCategory: Boolean = false
) {
    companion object {
        const val DEFAULT_QUESTION_AMOUNT = 3
    }
}
