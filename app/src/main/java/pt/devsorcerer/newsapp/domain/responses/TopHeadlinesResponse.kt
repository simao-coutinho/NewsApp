package pt.devsorcerer.newsapp.domain.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopHeadlinesResponse(
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int?,
    @SerialName("articles")
    val articles: List<ArticleResponse>?,
    @SerialName("code")
    val code: String?,
    @SerialName("message")
    val message: String?
)

sealed class Status(value: String) {
    object OK : Status("ok")
    object ERROR : Status("error")
}