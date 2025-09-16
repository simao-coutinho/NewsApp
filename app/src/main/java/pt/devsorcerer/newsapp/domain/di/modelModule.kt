package pt.devsorcerer.newsapp.domain.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pt.devsorcerer.newsapp.domain.implementation.HeadlinesRepositoryImpl
import pt.devsorcerer.newsapp.domain.repository.HeadlinesRepository

val modelModule = module {
    singleOf(::HeadlinesRepositoryImpl).bind(HeadlinesRepository::class)
}