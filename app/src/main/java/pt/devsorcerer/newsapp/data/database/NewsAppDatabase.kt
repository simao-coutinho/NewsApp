package pt.devsorcerer.newsapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.devsorcerer.newsapp.data.database.entities.ArticleEntity

@Database(
    version = 1,
    entities = [
        ArticleEntity::class
    ]
)
abstract class NewsAppDatabase: RoomDatabase() {
    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            NewsAppDatabase::class.java,
            "news_app.db"
        ).build()
    }
}