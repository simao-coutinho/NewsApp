package pt.devsorcerer.newsapp.domain.di

import org.koin.dsl.module
import io.ktor.client.engine.cio.CIO
import pt.devsorcerer.newsapp.data.database.NewsAppDatabase
import pt.devsorcerer.newsapp.data.networking.HttpClientFactory

val dataModule = module {
    single { NewsAppDatabase.build(get()) }
    single { HttpClientFactory.create(CIO.create())}
}