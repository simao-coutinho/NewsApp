package pt.devsorcerer.newsapp.domain.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopHeadlinesResponse(
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int? = null,
    @SerialName("articles")
    val articles: List<ArticleResponse>? = listOf(),
    @SerialName("code")
    val code: String? = null,
    @SerialName("message")
    val message: String? = null
) {
    sealed class Status(val value: String) {
        object OK : Status("ok")
        object ERROR : Status("error")
    }
}