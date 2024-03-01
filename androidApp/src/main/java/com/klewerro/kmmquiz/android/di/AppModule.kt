package com.klewerro.kmmquiz.android.di

import com.klewerro.kmmquiz.data.HttpClientFactory
import com.klewerro.kmmquiz.data.KtorQuestionClient
import com.klewerro.kmmquiz.domain.QuestionClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory().create()
    }

    @Provides
    @Singleton
    fun provideQuestionClient(httpClient: HttpClient): QuestionClient {
        return KtorQuestionClient(httpClient)
    }
}
