package com.project.githubsearch.network

import com.project.githubsearch.database.DatabaseRepo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkRepoContainer(val items: List<Items>)

data class Items(
    @Json(name = "id") var id: Int,
    @Json(name = "name") var name: String,
    @Json(name = "html_url") var html_url: String
)

fun NetworkRepoContainer.asDatabaseModel(): List<DatabaseRepo> {
    return items.map {
        DatabaseRepo(
            id = it.id,
            name = it.name,
            html_url = it.html_url
        )
    }
}
