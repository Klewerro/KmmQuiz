package com.klewerro.kmmquiz.android.root

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GetApp
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.outlined.GetApp
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.klewerro.kmmquiz.SharedRes
import com.klewerro.kmmquiz.android.core.BottomNavigationItem
import com.klewerro.kmmquiz.android.core.Route
import com.klewerro.kmmquiz.android.core.sharedStringResource

@Composable
fun KmmQuizBottomNav(
    navController: NavController,
    getQuestionsCount: Int?,
    savedQuestionsCount: Int?,
    quizCount: Int?
) {
    val navItems = listOf(
        BottomNavigationItem(
            title = sharedStringResource(id = SharedRes.strings.tab_get_questions),
            selectedIcon = Icons.Filled.GetApp,
            unselectedIcon = Icons.Outlined.GetApp,
            badgeCount = getQuestionsCount
        ),
        BottomNavigationItem(
            title = sharedStringResource(id = SharedRes.strings.tab_saved_questions),
            selectedIcon = Icons.Filled.Save,
            unselectedIcon = Icons.Outlined.Save,
            badgeCount = savedQuestionsCount
        ),
        BottomNavigationItem(
            title = sharedStringResource(id = SharedRes.strings.tab_quiz),
            selectedIcon = Icons.Filled.Quiz,
            unselectedIcon = Icons.Outlined.Quiz,
            badgeCount = quizCount
        )
    )

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
        navItems.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                selected = index == selectedItemIndex,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(
                        when (index) {
                            0 -> Route.GET_QUESTIONS
                            1 -> Route.SAVED_QUESTIONS
                            else -> Route.QUIZ
                        }
                    )
                },
                label = {
                    Text(text = bottomNavigationItem.title)
                },
                icon = {
                    BadgedBox(
                        badge = {
                            bottomNavigationItem.badgeCount?.let {
                                if (it > 0) {
                                    Badge {
                                        Text(text = it.toString())
                                    }
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (index == selectedItemIndex) {
                                bottomNavigationItem.selectedIcon
                            } else {
                                bottomNavigationItem.unselectedIcon
                            },
                            contentDescription = bottomNavigationItem.title
                        )
                    }
                }
            )
        }
    }
}
