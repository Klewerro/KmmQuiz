package com.klewerro.kmmquiz.data

import io.ktor.client.HttpClient

expect class HttpClientFactory {
    fun create(): HttpClient
}
