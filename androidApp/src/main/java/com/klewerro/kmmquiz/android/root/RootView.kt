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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.klewerro.kmmquiz.android.core.Route
import com.klewerro.kmmquiz.android.question.GetQuestionListAndroidViewModel
import com.klewerro.kmmquiz.android.question.GetQuestionListScreen
import com.klewerro.kmmquiz.android.quiz.QuizAndroidViewModel
import com.klewerro.kmmquiz.android.quiz.QuizScreen
import com.klewerro.kmmquiz.android.quiz.details.QuizDetailsAndroidViewModel
import com.klewerro.kmmquiz.android.quiz.details.QuizDetailsScreen
import com.klewerro.kmmquiz.android.saved.SavedQuestionsAndroidViewModel
import com.klewerro.kmmquiz.android.saved.SavedQuestionsScreen
import com.klewerro.kmmquiz.presentation.quiz.QuizEvent

@Composable
fun RootView() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val snackbarHostState = remember { SnackbarHostState() }
        val navController = rememberNavController()

        val getQuestionListViewModel = hiltViewModel<GetQuestionListAndroidViewModel>()
        val getQuestionListState by getQuestionListViewModel.state.collectAsStateWithLifecycle()

        val savedQuestionsViewModel = hiltViewModel<SavedQuestionsAndroidViewModel>()
        val savedQuestionsState by savedQuestionsViewModel.state.collectAsStateWithLifecycle()

        val quizViewModel = hiltViewModel<QuizAndroidViewModel>()
        val quizState by quizViewModel.state.collectAsStateWithLifecycle()

        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar = {
                KmmQuizBottomNav(
                    navController,
                    getQuestionListState.questions.size,
                    savedQuestionsState.savedQuestions.size,
                    quizState.quizList.size
                )
            }
        ) { scaffoldPaddingValues ->

            NavHost(
                navController = navController,
                startDestination = Route.GET_QUESTIONS
            ) {
                composable(Route.GET_QUESTIONS) {
                    GetQuestionListScreen(
                        state = getQuestionListState,
                        onEvent = { event ->
                            getQuestionListViewModel.onEvent(event)
                        },
                        snackbarHostState = snackbarHostState,
                        modifier = Modifier.padding(scaffoldPaddingValues)
                    )
                }
                composable(Route.SAVED_QUESTIONS) {
                    SavedQuestionsScreen(
                        state = savedQuestionsState,
                        onEvent = { event ->
                            savedQuestionsViewModel.onEvent(event)
                        },
                        modifier = Modifier.padding(scaffoldPaddingValues),
                        snackbarHostState = snackbarHostState
                    )
                }
                navigation(route = Route.QUIZ_NAVIGATION, startDestination = Route.QUIZ) {
                    composable(Route.QUIZ) {
                        QuizScreen(
                            state = quizState,
                            onEvent = { event ->
                                quizViewModel.onEvent(event)
                                if (event is QuizEvent.GoToQuestions) {
                                    navController.navigate(Route.QUIZ_DETAILS + "/${event.quizId}")
                                }
                            },
                            modifier = Modifier.padding(scaffoldPaddingValues)
                        )
                    }
                    composable(
                        route = Route.QUIZ_DETAILS + "/{quiz_id}",
                        arguments = listOf(navArgument("quiz_id") { type = NavType.LongType })
                    ) {
                        val quizDetailsViewModel = hiltViewModel<QuizDetailsAndroidViewModel>()
                        val quizDetailsState by quizDetailsViewModel.state.collectAsStateWithLifecycle()
                        QuizDetailsScreen(
                            quizDetailsState,
                            onEvent = { event ->
                                quizDetailsViewModel.onEvent(event)
                            },
                            onCloseClick = {
                                navController.popBackStack()
                            },
                            modifier = Modifier.padding(scaffoldPaddingValues)
                        )
                    }
                }
            }
        }
    }
}
