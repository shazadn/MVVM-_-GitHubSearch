package com.project.githubsearch.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.githubsearch.domain.Repo

@Entity
data class DatabaseRepo(
    @PrimaryKey
    var id: Int,
    var name: String,
    var html_url: String
)

fun List<DatabaseRepo>.asDomainModel(): List<Repo> {
    return map {
        Repo(
            id = it.id,
            name = it.name,
            html_url = it.html_url
        )
    }
 }