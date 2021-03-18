package com.project.githubsearch.di

import com.project.githubsearch.network.Repos_APIServices
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun provideUserApi(retrofit: Retrofit): Repos_APIServices{
        return retrofit.create(Repos_APIServices::class.java)
    }

    single { provideUserApi(get()) }
}