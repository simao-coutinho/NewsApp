package pt.devsorcerer.newsapp.presentation.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import pt.devsorcerer.newsapp.domain.model.Article
import pt.devsorcerer.newsapp.presentation.features.headlines.HeadlinesState
import pt.devsorcerer.newsapp.presentation.features.headlines.HeadlinesViewModel
import pt.devsorcerer.newsapp.presentation.ui.components.ArticleItem
import pt.devsorcerer.newsapp.presentation.ui.theme.NewsAppTheme

@Composable
fun HeadlinesScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: HeadlinesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val articles by viewModel.articles.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.init()
    }

    HeadlinesScreen(
        modifier = modifier,
        state = state,
        articles = articles
    )
}

@Composable
fun HeadlinesScreen(
    modifier: Modifier = Modifier,
    state: HeadlinesState,
    articles: List<Article> = emptyList()
) {
    Scaffold { innerPadding ->
        val lazyListState = rememberLazyListState()

        LazyColumn(
            modifier = modifier.padding(innerPadding),
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(articles) { article ->
                ArticleItem(article = article)
            }
        }

        AnimatedVisibility(
            state.isLoading
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
private fun HeadlinesScreenPreview() {
    NewsAppTheme {
        HeadlinesScreen(
            state = HeadlinesState(
                isLoading = true
            )
        )
    }
}