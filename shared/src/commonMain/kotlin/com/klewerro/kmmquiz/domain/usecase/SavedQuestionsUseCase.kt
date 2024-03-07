package com.klewerro.kmmquiz.domain.usecase

import com.klewerro.kmmquiz.domain.LocalDbDataSource
import com.klewerro.kmmquiz.domain.mapper.QuestionEntityMapper.mapToQuestion
import com.klewerro.kmmquiz.domain.util.flow.toCommonFlow
import kotlinx.coroutines.flow.map

class SavedQuestionsUseCase(private val localDbDataSource: LocalDbDataSource) {
    fun execute() = localDbDataSource.questions
        .map {
            it.map { questionEntity ->
                questionEntity.mapToQuestion()
            }
        }
        .toCommonFlow()
}
