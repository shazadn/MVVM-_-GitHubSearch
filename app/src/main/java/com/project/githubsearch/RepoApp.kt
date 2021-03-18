package com.project.githubsearch

import android.app.Application
import com.project.githubsearch.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class RepoApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RepoApp)
            androidLogger(Level.DEBUG)
            modules(viewModelModule, repositoryModule, netModule, apiModule, databaseModule)
        }
    }
}