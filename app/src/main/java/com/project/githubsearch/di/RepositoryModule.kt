package com.project.githubsearch.di

import com.project.githubsearch.database.RepoDatabase
import com.project.githubsearch.network.Repos_APIServices
import com.project.githubsearch.repository.RepoRepository
import org.koin.dsl.module

val repositoryModule = module {
    fun provideRepository(api: Repos_APIServices, dao: RepoDatabase): RepoRepository {
        return RepoRepository(api, dao)
    }

    single {
        provideRepository(get(), get())
    }
}