package com.project.githubsearch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.project.githubsearch.database.RepoDatabase
import com.project.githubsearch.database.asDomainModel
import com.project.githubsearch.domain.Repo
import com.project.githubsearch.network.API_Calls
import com.project.githubsearch.network.Repos_APIServices
import com.project.githubsearch.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoRepository (
    private val reposApiservices: Repos_APIServices,
    private val database: RepoDatabase
) {

    suspend fun refreshRepos() {
        withContext(Dispatchers.IO) {
            val repo = reposApiservices.searchRepos(API_Calls.Query_String_Repo).await()
            database.repoDao.insertAll(repo.asDatabaseModel())
        }
    }

    val results: LiveData<List<Repo>> = Transformations.map(database.repoDao.getLocalDBRepos()){
        it.asDomainModel()
    }


//    suspend fun searchRepos() {
//
//    }

}