package com.project.githubsearch

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.githubsearch.domain.Repo
import com.project.githubsearch.recycler_adapters.RepoAdapter
import com.project.githubsearch.viewmodel.RepoViewModel
import com.project.githubsearch.viewmodel.SearchRepoViewModel
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var viewModelAdapter: RepoAdapter? = null

    private val repoViewModel by viewModel<RepoViewModel>()
    private val viewModel by viewModel<SearchRepoViewModel>()

    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpObserverToGetData()
        searchableRepos()

        search_term.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                //Do not perform
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Do not perform
            }

            override fun onTextChanged(query: CharSequence, p1: Int, p2: Int, p3: Int) {
                queriedRepos(query)
            }

        })
    }


    private fun queriedRepos(query: CharSequence) {
        if (query.isNotEmpty()) {
            val formatQuery: String = query.toString().toLowerCase(Locale.getDefault())
            viewModel.filter(formatQuery).observe(viewLifecycleOwner, Observer { repos ->
                repos?.apply {
                    viewModelAdapter?.results = repos
                }

            })
        } else {
            searchableRepos()
        }
    }

    private fun searchableRepos() {
        viewModel.repos.observe(viewLifecycleOwner, Observer { repos ->
            viewModelAdapter?.results = repos
        })

    }


    private fun setUpObserverToGetData() {
        repoViewModel.repoResults.observe(viewLifecycleOwner, Observer<List<Repo>> { repo ->
            repo?.apply {
                viewModelAdapter?.results = repo
//                Log.i("Repo result: ", repo.get(1).name.toString())
//                Log.i("Repo result: ", repo.get(1).html_url.toString())
            }
        })
    }

    private fun setUpRecyclerView() {
        viewModelAdapter = RepoAdapter()
        rv_repo.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }
    }


}