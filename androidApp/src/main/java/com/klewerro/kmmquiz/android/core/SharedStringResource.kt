package com.klewerro.kmmquiz.android.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.klewerro.kmmquiz.SharedStrings
import dev.icerock.moko.resources.StringResource

@Composable
fun sharedStringResource(id: StringResource, vararg args: Any): String {
    return SharedStrings(LocalContext.current).get(id, args.toList())
}
