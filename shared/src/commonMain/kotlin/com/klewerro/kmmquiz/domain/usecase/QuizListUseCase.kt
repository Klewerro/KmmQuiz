package com.klewerro.kmmquiz.domain.usecase

import com.klewerro.kmmquiz.domain.LocalDbDataSource
import com.klewerro.kmmquiz.domain.mapper.QuizEntityMapper.mapToQuiz
import com.klewerro.kmmquiz.domain.util.flow.toCommonFlow
import kotlinx.coroutines.flow.map

class QuizListUseCase(private val localDbDataSource: LocalDbDataSource) {
    fun execute() = localDbDataSource.quizList
        .map {
            it.map { quizEntity ->
                quizEntity.mapToQuiz(
                    localDbDataSource.getQuizQuestions(quizEntity.id)
                )
            }
        }
        .toCommonFlow()
}
