package pt.devsorcerer.newsapp.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
        articles = articles,
        loadNextItems = { viewModel.loadMoreItems() }
    )
}

@Composable
fun HeadlinesScreen(
    modifier: Modifier = Modifier,
    state: HeadlinesState,
    articles: List<Article> = emptyList(),
    loadNextItems: () -> Unit = {},
) {
    Scaffold { innerPadding ->
        val lazyListState = rememberLazyListState()

        val shouldLoadMore by remember(articles, state.isLoading) {
            derivedStateOf {
                val lastVisibleItem = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()
                    ?: return@derivedStateOf false

                if (articles.isEmpty()) return@derivedStateOf false

                // Condition:
                // 1. The user has scrolled past the first item.
                // 2. The last visible item is near the end of the list.
                // 3. We are not already loading.
                lazyListState.firstVisibleItemIndex > 0 &&
                        lastVisibleItem.index >= articles.size - 5 &&
                        !state.isLoading
            }
        }

        LaunchedEffect(shouldLoadMore) {
            if (shouldLoadMore) {
                Log.d("HeadlinesScreen", "Loading next items...")
                loadNextItems()
            }
        }


        LazyColumn(
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp),
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(articles) { article ->
                ArticleItem(article = article)
            }

            // Show a loading indicator at the bottom when fetching more items
            if (state.isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
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