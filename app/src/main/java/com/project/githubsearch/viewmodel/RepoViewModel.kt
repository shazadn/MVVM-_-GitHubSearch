package com.project.githubsearch.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.project.githubsearch.repository.RepoRepository
import kotlinx.coroutines.*

class RepoViewModel (private val repoRepository: RepoRepository) : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val repoResults = repoRepository.results


    init {
        refreshFromRepository()
    }

    fun refreshFromRepository() {
        viewModelScope.launch {
            try {
                repoRepository.refreshRepos()
            } catch (networkError: Exception) {
                Log.e("Repo error", networkError.localizedMessage)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}