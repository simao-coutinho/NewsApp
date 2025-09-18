package pt.devsorcerer.newsapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import pt.devsorcerer.newsapp.domain.model.Article
import pt.devsorcerer.newsapp.presentation.ui.theme.NewsAppTheme

@Composable
fun ArticleItem(
    modifier: Modifier = Modifier,
    article: Article
) {
    Card(
        modifier = modifier
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (article.urlToImage != null) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = article.urlToImage
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape( topStart = 16.dp, topEnd = 16.dp)),
                )
            }

            Column (
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            ) {
                Text(
                    text = article.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Text(
                            text = article.author ?: "",
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    }

                    Text(
                        text = formatDate(publishedAt = article.publishedAt),
                        fontSize = 10.sp
                    )
                }
            }
        }
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