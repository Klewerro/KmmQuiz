package com.klewerro.kmmquiz

import kotlin.test.Test
import kotlin.test.assertTrue

class GreetingTestIos {
    @Test
    fun testExample() {
        assertTrue(Greeting().greet().contains("iOS"), "Check iOS is mentioned")
    }
}
