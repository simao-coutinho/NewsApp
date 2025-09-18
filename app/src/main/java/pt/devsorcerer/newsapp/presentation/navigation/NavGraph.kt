package pt.devsorcerer.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import pt.devsorcerer.newsapp.domain.model.Article
import pt.devsorcerer.newsapp.presentation.ui.screens.DetailsScreenRoot
import pt.devsorcerer.newsapp.presentation.ui.screens.HeadlinesScreenRoot

sealed class Screen {
    @Serializable
    object Headlines
    @Serializable
    data class Details(
        val author: String?,
        val title: String,
        val description: String?,
        val url: String,
        val urlToImage: String?,
        val publishedAt: String,
        val content: String?
    )
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Headlines) {
        composable<Screen.Headlines> {
            HeadlinesScreenRoot(
                modifier = Modifier,
                onNavigateToArticleDetails = { article ->
                    navController.navigate(Screen.Details(
                        article.author,
                        article.title,
                        article.description,
                        article.url,
                        article.urlToImage,
                        article.publishedAt,
                        article.content
                    ))
                }
            )
        }
        composable<Screen.Details> { backStackEntry ->
            val article: Article = backStackEntry.toRoute()
            DetailsScreenRoot(article = article)
        }
    }
}