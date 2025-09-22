package pt.devsorcerer.newsapp.presentation.ui.components

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import pt.devsorcerer.newsapp.R
import pt.devsorcerer.newsapp.domain.model.Article

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ArticleItem(
    modifier: Modifier = Modifier,
    article: Article,
    onArticleClicked: () -> Unit = {},
    animatedContentScope: AnimatedContentScope,
) {
    Card(
        modifier = modifier
            .clickable(onClick = {
                onArticleClicked()
            })
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
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "image-${article.url}"),
                            animatedVisibilityScope = animatedContentScope,
                        ),
                )
            }

            Column (
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            ) {
                Text(
                    text = article.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .sharedElement( // Apply modifier with the SAME key
                            sharedContentState = rememberSharedContentState(key = "title-${article.url}"),
                            animatedVisibilityScope = animatedContentScope,
                        )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.ic_person),
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = stringResource(R.string.author),
                            modifier = Modifier.padding(end = 4.dp).size(20.dp)
                        )

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