package pt.devsorcerer.newsapp.domain.di

import org.koin.dsl.module
import pt.devsorcerer.newsapp.data.database.NewsAppDatabase

val dataModule = module {
    single { NewsAppDatabase.build(get()) }
}