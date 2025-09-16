package pt.devsorcerer.newsapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import pt.devsorcerer.newsapp.model.di.dataModule
import pt.devsorcerer.newsapp.model.di.modelModule
import pt.devsorcerer.newsapp.model.di.presentationModule

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@NewsApp)
            modules(
                listOf(
                    dataModule,
                    presentationModule,
                    modelModule
                )
            )
        }
    }
}