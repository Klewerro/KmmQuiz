package com.klewerro.kmmquiz

import dev.icerock.moko.resources.StringResource

expect class SharedStrings {
    fun get(
        id: StringResource,
        args: List<Any>
    ): String
}
