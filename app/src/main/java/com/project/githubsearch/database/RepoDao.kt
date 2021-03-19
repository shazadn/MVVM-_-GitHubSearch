package com.project.githubsearch.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(repos: List<DatabaseRepo>)

    @Query("SELECT * FROM DatabaseRepo")
    fun getLocalDBRepos(): LiveData<List<DatabaseRepo>>

    @Query ("SELECT * FROM DatabaseRepo")
    fun getSearchedRepoByName(): List<DatabaseRepo>


}

@Database(entities = [DatabaseRepo::class], version = 4, exportSchema = false)
abstract class RepoDatabase : RoomDatabase() {
    abstract val repoDao: RepoDao
}