package pt.devsorcerer.newsapp.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pt.devsorcerer.newsapp.data.database.entities.ArticleEntity

@Dao
interface ArticleDao : RoomDao<ArticleEntity> {

    @Query("SELECT * FROM articles")
    fun getArticles(): Flow<List<ArticleEntity>>


}