package com.project.githubsearch.di

import com.project.githubsearch.viewmodel.RepoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RepoViewModel(get()) }
}
