package pt.devsorcerer.newsapp.domain.implementation

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pt.devsorcerer.newsapp.data.database.NewsAppDatabase
import pt.devsorcerer.newsapp.data.networking.safeCall
import pt.devsorcerer.newsapp.domain.mappers.toDomain
import pt.devsorcerer.newsapp.domain.mappers.toEntity
import pt.devsorcerer.newsapp.domain.model.Article
import pt.devsorcerer.newsapp.domain.repository.HeadlinesRepository
import pt.devsorcerer.newsapp.domain.responses.TopHeadlinesResponse
import pt.devsorcerer.newsapp.domain.util.CallResult
import pt.devsorcerer.newsapp.domain.util.NetworkError
import pt.devsorcerer.newsapp.domain.util.map

class HeadlinesRepositoryImpl(
    private val database: NewsAppDatabase,
    private val httpClient: HttpClient,
) : HeadlinesRepository {
    override fun getHeadlines(): Flow<List<Article>> {
        return database.articleDao.getArticles().map { list ->
            list.map { articleEntity ->
                articleEntity.toDomain()
            }
        }
    }

    override fun saveArticles(articles: List<Article>) {
        val articleEntities = articles.map { article ->
            article.toEntity()
        }
        database.articleDao.upsert(articleEntities)
    }

    override suspend fun getRemoteHeadlines(page: Int): CallResult<List<Article>, NetworkError> {
        return safeCall<TopHeadlinesResponse> {
            httpClient.get(
                urlString = "https://newsapi.org/v2/top-headlines?language=en&page=$page&apiKey=766ccbd2dab84770880b8035b74cef21"
            )
        }.map { response ->
            if (response.status == TopHeadlinesResponse.Status.OK.value) {
                response.articles!!.map { articleResponse ->
                    articleResponse.toDomain()
                }
            } else {
                emptyList()
            }
        }
    }

}