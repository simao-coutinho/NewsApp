package pt.devsorcerer.newsapp.model.repository

import kotlinx.coroutines.flow.Flow
import pt.devsorcerer.newsapp.model.model.Article

interface HeadlinesRepository {
    fun getHeadlines(country: String): Flow<List<Article>>
}