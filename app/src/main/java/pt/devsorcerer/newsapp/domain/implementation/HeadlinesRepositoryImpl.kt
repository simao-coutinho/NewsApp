package pt.devsorcerer.newsapp.domain.implementation

import com.plcoding.cryptotracker.core.data.networking.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pt.devsorcerer.newsapp.data.database.NewsAppDatabase
import pt.devsorcerer.newsapp.domain.mappers.toDomain
import pt.devsorcerer.newsapp.domain.model.Article
import pt.devsorcerer.newsapp.domain.repository.HeadlinesRepository
import pt.devsorcerer.newsapp.domain.responses.TopHeadlinesResponse
import pt.devsorcerer.newsapp.domain.util.CallResult
import pt.devsorcerer.newsapp.domain.util.NetworkError

class HeadlinesRepositoryImpl(
    private val database: NewsAppDatabase,
    private val httpClient: HttpClient,
): HeadlinesRepository {
    override fun getHeadlines(country: String): Flow<List<Article>> {


        return database.articleDao.getArticles().map { list ->
            list.map { articleEntity ->
                articleEntity.toDomain()
            }
        }
    }

    override suspend fun getRemoteHeadlines(country: String): CallResult<List<Article>, NetworkError> {
        return safeCall<TopHeadlinesResponse> {
            httpClient.get(
                urlString = "https://newsapi.org/v2/top-headlines?country=us&apiKey=766ccbd2dab84770880b8035b74cef21"
            )
        }
    }

}