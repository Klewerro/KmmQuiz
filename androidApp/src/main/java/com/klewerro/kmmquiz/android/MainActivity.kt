package com.klewerro.kmmquiz.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.klewerro.kmmquiz.SharedRes
import com.klewerro.kmmquiz.SharedStrings
import dev.icerock.moko.resources.StringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingView(sharedStringResource(id = SharedRes.strings.hello_world))
                }
            }
        }
    }
}

@Composable
fun sharedStringResource(
    id: StringResource,
    vararg args: Any
): String {
    return SharedStrings(LocalContext.current).get(id, args.toList())
}

@Composable
fun GreetingView(text: String) {
    Text(
        text = text,
        modifier =
            Modifier
                .background(MaterialTheme.colorScheme.primary),
        color = MaterialTheme.colorScheme.onPrimary
    )
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
