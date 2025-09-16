package pt.devsorcerer.newsapp.data.database.entities

import androidx.room.Entity

@Entity(
    tableName = "articles",
    primaryKeys = ["title", "url"]
)
data class ArticleEntity(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)