package pt.devsorcerer.newsapp.presentation.ui.screens

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.koinViewModel
import pt.devsorcerer.newsapp.R
import pt.devsorcerer.newsapp.domain.model.Article
import pt.devsorcerer.newsapp.presentation.features.details.DetailsViewModel
import pt.devsorcerer.newsapp.presentation.ui.components.calculateReadingTime
import pt.devsorcerer.newsapp.presentation.ui.components.formatDate
import pt.devsorcerer.newsapp.presentation.ui.theme.NewsAppTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreenRoot(
    modifier: Modifier = Modifier,
    article: Article,
    viewModel: DetailsViewModel = koinViewModel(),
    animatedContentScope: AnimatedContentScope
) {
    DetailsScreen(
        modifier = modifier,
        article = article,
        animatedContentScope = animatedContentScope
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreen(
    modifier: Modifier = Modifier,
    article: Article,
    animatedContentScope: AnimatedContentScope,
) {
    Scaffold { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = article.urlToImage
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .sharedElement(
                        sharedContentState = rememberSharedContentState(key = "image-${article.url}"),
                        animatedVisibilityScope = animatedContentScope
                    )
            )

            Column(
                modifier = modifier
                    .padding(top = 280.dp)
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    )
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Text(
                    text = article.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(16.dp)
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "title-${article.url}"),
                            animatedVisibilityScope = animatedContentScope
                        )
                )

                Card(
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Row (
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_person),
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = stringResource(R.string.author),
                                modifier = Modifier.padding(end = 4.dp).size(20.dp)
                            )

                            Column(
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = article.author ?: "",
                                    fontWeight = FontWeight.Bold,
                                )

                                Text(
                                    text = formatDate(publishedAt = article.publishedAt),
                                )
                            }

                            Icon(
                                painter = painterResource(R.drawable.ic_time),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )

                            Text(
                                text = calculateReadingTime(article.content),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.defaultMinSize(48.dp)
                            )
                        }
                    }
                }

                Text(
                    text = article.content ?: "",
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
                )
            }
        }
    }
}