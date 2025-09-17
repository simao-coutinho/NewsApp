package pt.devsorcerer.newsapp.domain.mappers

import pt.devsorcerer.newsapp.domain.model.Article
import pt.devsorcerer.newsapp.domain.responses.ArticleResponse

fun ArticleResponse.toDomain() =
    Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        title = title,
        url = url,
        urlToImage = urlToImage
    )