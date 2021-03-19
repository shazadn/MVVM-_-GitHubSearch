package com.project.githubsearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.githubsearch.database.DatabaseRepo
import com.project.githubsearch.domain.Repo
import com.project.githubsearch.repository.RepoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class SearchRepoViewModel (private val repoRepository: RepoRepository) : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _repos: LiveData<List<Repo>> = repoRepository.results
    val repos: LiveData<List<Repo>>
        get() = _repos

    private lateinit var searchableRepo: List<Repo>
    private val filteredList = MutableLiveData<List<Repo>>()

    init {
        viewModelScope.launch {
            searchableRepo = repoRepository.searchRepo()
        }
    }

    fun filter(query: String): LiveData<List<Repo>> {
        filterWithQuery(query)
        return filteredList
    }

    private fun filterWithQuery(query: String) {
        val filterList = ArrayList<Repo>()
        for (repo: Repo in searchableRepo) {
            val formatTitle: String = repo.name.toLowerCase(Locale.getDefault())
            if (formatTitle.contains(query)) {
                filterList.add(repo)
            }
        }
        filteredList.value = filterList
    }
}