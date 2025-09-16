package pt.devsorcerer.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import pt.devsorcerer.newsapp.presentation.navigation.NavGraph
import pt.devsorcerer.newsapp.presentation.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NewsAppTheme {
                NavGraph(navController = navController)
            }
        }
    }
}