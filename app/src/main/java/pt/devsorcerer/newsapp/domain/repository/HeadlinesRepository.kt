package pt.devsorcerer.newsapp.domain.repository

import kotlinx.coroutines.flow.Flow
import pt.devsorcerer.newsapp.domain.model.Article
import pt.devsorcerer.newsapp.domain.util.CallResult
import pt.devsorcerer.newsapp.domain.util.NetworkError

interface HeadlinesRepository {
    fun getHeadlines(country: String): Flow<List<Article>>
    fun saveArticles(articles: List<Article>)
    suspend fun getRemoteHeadlines(country: String): CallResult<List<Article>, NetworkError>
}