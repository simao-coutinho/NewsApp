package pt.devsorcerer.newsapp.domain.mappers

import pt.devsorcerer.newsapp.data.database.entities.ArticleEntity
import pt.devsorcerer.newsapp.domain.model.Article

fun ArticleEntity.toDomain() = Article(
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content
)

fun Article.toEntity() = ArticleEntity(
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content
)