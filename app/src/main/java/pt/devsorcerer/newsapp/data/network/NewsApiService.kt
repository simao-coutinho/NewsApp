package pt.devsorcerer.newsapp.data.network

import com.plcoding.cryptotracker.core.domain.util.NetworkError
import com.plcoding.cryptotracker.core.domain.util.CallResult
import pt.devsorcerer.newsapp.domain.responses.TopHeadlinesResponse

interface NewsApiService {
    suspend fun getArticles(): CallResult<TopHeadlinesResponse, NetworkError>
}