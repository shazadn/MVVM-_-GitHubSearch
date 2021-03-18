package com.project.githubsearch.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface Repos_APIServices {

    @GET(API_Calls.API_SEARCH_REPOSITORIES)
    fun searchRepos(@Query("q") searchTerm: String): Deferred<NetworkRepoContainer>
}