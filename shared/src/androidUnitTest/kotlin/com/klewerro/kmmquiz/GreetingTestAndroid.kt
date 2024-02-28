package com.klewerro.kmmquiz

import org.junit.Assert.assertTrue
import org.junit.Test

class GreetingTestAndroid {
    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", Greeting().greet().contains("Android"))
    }
}
