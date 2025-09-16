package pt.devsorcerer.newsapp.model.mappers

import pt.devsorcerer.newsapp.data.database.entities.ArticleEntity
import pt.devsorcerer.newsapp.model.model.Article

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