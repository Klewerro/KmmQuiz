package com.klewerro.kmmquiz.android.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.klewerro.kmmquiz.android.core.Route
import com.klewerro.kmmquiz.android.question.GetQuestionListAndroidViewModel
import com.klewerro.kmmquiz.android.question.GetQuestionListScreen
import com.klewerro.kmmquiz.android.saved.SavedQuestionsAndroidViewModel
import com.klewerro.kmmquiz.android.saved.SavedQuestionsScreen

@Composable
fun RootView() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val snackbarHostState = remember { SnackbarHostState() }
        val navController = rememberNavController()

        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar = {
                KmmQuizBottomNav(navController)
            }
        ) { paddingValues ->

            NavHost(
                navController = navController,
                startDestination = Route.GET_QUESTIONS
            ) {
                composable(Route.GET_QUESTIONS) {
                    val viewModel = hiltViewModel<GetQuestionListAndroidViewModel>()
                    val getQuestionListState by viewModel.state.collectAsStateWithLifecycle()
                    GetQuestionListScreen(
                        state = getQuestionListState,
                        onEvent = { event ->
                            viewModel.onEvent(event)
                        },
                        snackbarHostState = snackbarHostState,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
                composable(Route.SAVED_QUESTIONS) {
                    val viewModel = hiltViewModel<SavedQuestionsAndroidViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    SavedQuestionsScreen(
                        state = state,
                        onEvent = { event ->
                            viewModel.onEvent(event)
                        }
                    )
                }
            }
        }
    }
}
