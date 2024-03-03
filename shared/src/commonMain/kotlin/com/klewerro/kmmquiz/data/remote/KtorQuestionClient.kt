package com.klewerro.kmmquiz.data.remote

import com.klewerro.kmmquiz.data.remote.dto.QuestionResponseDto
import com.klewerro.kmmquiz.domain.QuestionClient
import com.klewerro.kmmquiz.domain.mapper.QuestionMapper
import com.klewerro.kmmquiz.domain.model.GetQuestionListError
import com.klewerro.kmmquiz.domain.model.question.Question
import com.klewerro.kmmquiz.domain.model.question.QuestionCategory
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.errors.IOException

class KtorQuestionClient(private val httpClient: HttpClient) : QuestionClient {
    override suspend fun getQuestions(questionCategory: QuestionCategory, amount: Int): List<Question> {
        val questionsRequestResult =
            httpClient
                .get {
                    url(ApiConstants.BASE_URL)
                    parameter(ApiConstants.PARAMETER_AMOUNT, amount)
                    parameter(ApiConstants.PARAMETER_CATEGORY, questionCategory.id)
                }
                .runThrowing<QuestionResponseDto>()
        return questionsRequestResult.questionDtoList.map { QuestionMapper.map(it) }
    }

    private suspend inline fun <reified T> HttpResponse.runThrowing(): T {
        val result =
            try {
                this.body<T>()
            } catch (e: IOException) {
                throw QuestionApiException(GetQuestionListError.SERVICE_UNAVAILABLE)
            } catch (e: NoTransformationFoundException) {
                throw QuestionApiException(GetQuestionListError.SERIALIZATION_ERROR)
            } catch (e: Exception) {
                if (this.status.value == 429) {
                    throw QuestionApiException(GetQuestionListError.TOO_MANY_REQUESTS_ERROR)
                } else {
                    throw QuestionApiException(GetQuestionListError.SERVER_ERROR)
                }
            }

        when (this.status.value) {
            in 200..299 -> Unit
            500 -> throw QuestionApiException(GetQuestionListError.SERVER_ERROR)
            429 -> throw QuestionApiException(GetQuestionListError.TOO_MANY_REQUESTS_ERROR)
            in 400..499 -> throw QuestionApiException(GetQuestionListError.CLIENT_ERROR)
            else -> throw QuestionApiException(GetQuestionListError.UNKNOWN_ERROR)
        }

        return result
    }
}
