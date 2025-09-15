package pt.devsorcerer.newsapp.presentation.features.headlines

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.devsorcerer.newsapp.presentation.ui.theme.NewsAppTheme

@Composable
fun HeadlinesScreenRoot(modifier: Modifier = Modifier) {

}

@Composable
fun HeadlinesScreen(modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        val lazyListState = rememberLazyListState()

        LazyColumn(
            modifier = modifier.padding(innerPadding),
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

        }

    }
}

@Preview
@Composable
private fun HeadlinesScreenPreview() {
    NewsAppTheme {
        HeadlinesScreen()
    }
}