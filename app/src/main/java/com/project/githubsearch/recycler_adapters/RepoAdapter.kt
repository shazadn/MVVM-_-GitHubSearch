package com.project.githubsearch.recycler_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.githubsearch.R
import com.project.githubsearch.domain.Repo
import kotlinx.android.synthetic.main.row_repo.view.*

class RepoAdapter : RecyclerView.Adapter<RepoViewHolder>() {

    var results: List<Repo> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_repo, parent, false))
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder?.tvRepoName?.text = results.get(position).name
        holder?.tvRepoUrl?.text = results.get(position).html_url
    }
}


class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvRepoName = view.tvRepoName
    val tvRepoUrl = view.tvRepoUrl
}
