package pt.devsorcerer.newsapp.model.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pt.devsorcerer.newsapp.data.database.NewsAppDatabase
import pt.devsorcerer.newsapp.model.mappers.toDomain
import pt.devsorcerer.newsapp.model.model.Article
import pt.devsorcerer.newsapp.model.repository.HeadlinesRepository

class HeadlinesRepositoryImpl(
    private val database: NewsAppDatabase
): HeadlinesRepository {
    override fun getHeadlines(country: String): Flow<List<Article>> {
        return database.articleDao.getArticles().map { list ->
            list.map { articleEntity ->
                articleEntity.toDomain()
            }
        }
    }

}