package pt.devsorcerer.newsapp.model.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pt.devsorcerer.newsapp.model.implementation.HeadlinesRepositoryImpl
import pt.devsorcerer.newsapp.model.repository.HeadlinesRepository

val modelModule = module {
    singleOf(::HeadlinesRepositoryImpl).bind(HeadlinesRepository::class)
}