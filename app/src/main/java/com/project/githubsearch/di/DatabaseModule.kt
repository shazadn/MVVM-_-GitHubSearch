package com.project.githubsearch.di

import android.app.Application
import androidx.room.Room
import com.project.githubsearch.database.RepoDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun providesDatabase(application: Application): RepoDatabase {
        return Room.databaseBuilder(application, RepoDatabase::class.java, "jokes.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    // singleton: single instance
    single { providesDatabase(androidApplication()) } // lifecycle of the application
}