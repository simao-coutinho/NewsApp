package pt.devsorcerer.newsapp.domain.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import pt.devsorcerer.newsapp.presentation.features.headlines.HeadlinesViewModel

val presentationModule = module {
    viewModelOf(::HeadlinesViewModel)
}