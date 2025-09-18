package pt.devsorcerer.newsapp.domain.repository

import kotlinx.coroutines.flow.Flow
import pt.devsorcerer.newsapp.domain.model.Article
import pt.devsorcerer.newsapp.domain.util.CallResult
import pt.devsorcerer.newsapp.domain.util.NetworkError

interface HeadlinesRepository {
    fun getHeadlines(): Flow<List<Article>>
    fun saveArticles(articles: List<Article>)
    suspend fun getRemoteHeadlines(page: Int = 1): CallResult<List<Article>, NetworkError>
}