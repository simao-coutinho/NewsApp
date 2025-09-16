package pt.devsorcerer.newsapp.presentation.ui.components

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pt.devsorcerer.newsapp.domain.model.Article
import pt.devsorcerer.newsapp.presentation.ui.theme.NewsAppTheme

@Composable
fun ArticleItem(
    modifier: Modifier = Modifier,
    article: Article
) {
    Card {
        Text(
            text = article.title
        )
    }
}

@Preview
@Composable
private fun ArticleItemPreview() {
    NewsAppTheme {
        ArticleItem(
            article = Article(
                author = "m",
                title = "m",
                description = "m",
                url = "m",
                urlToImage = "m",
                publishedAt = "m",
                content = "m"
            )
        )
    }
}