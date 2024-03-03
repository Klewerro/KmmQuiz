package com.klewerro.kmmquiz.android.di

import com.klewerro.kmmquiz.domain.LocalDbDataSource
import com.klewerro.kmmquiz.domain.QuestionClient
import com.klewerro.kmmquiz.domain.usecase.GetQuestionListUseCase
import com.klewerro.kmmquiz.domain.usecase.QuizListUseCase
import com.klewerro.kmmquiz.domain.usecase.SavedQuestionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideGetQuestionListUseCase(questionClient: QuestionClient): GetQuestionListUseCase {
        return GetQuestionListUseCase(questionClient)
    }

    @Provides
    @ViewModelScoped
    fun provideSavedQuestionsUseCase(localDbDataSource: LocalDbDataSource): SavedQuestionsUseCase {
        return SavedQuestionsUseCase(localDbDataSource)
    }

    @Provides
    @ViewModelScoped
    fun provideQuizListUseCase(localDbDataSource: LocalDbDataSource): QuizListUseCase {
        return QuizListUseCase(localDbDataSource)
    }
}
