package pt.devsorcerer.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pt.devsorcerer.newsapp.presentation.ui.screens.HeadlinesScreenRoot

sealed class Screen(val route: String) {
    object Headlines: Screen("headlines")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Headlines.route
    ) {
        composable(Screen.Headlines.route) {
            HeadlinesScreenRoot(modifier = Modifier)
        }

    }
}