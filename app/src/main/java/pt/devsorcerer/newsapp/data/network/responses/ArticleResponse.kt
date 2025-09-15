package pt.devsorcerer.newsapp.data.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    @SerialName("source")
    val source: SourceResponse,
    @SerialName("author")
    val author: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("url")
    val url: String,
    @SerialName("urlToImage")
    val urlToImage: String,
    @SerialName("publishedAt")
    val publishedAt: String,
    @SerialName("content")
    val content: String,
)

@Serializable
data class SourceResponse(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
)