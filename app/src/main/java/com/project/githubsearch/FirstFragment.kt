package com.project.githubsearch

import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var viewModelAdapter: RepoAdapter? = null

    private val viewModel by viewModel<RepoViewModel>()

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

    }

    private fun setUpObserverToGetData() {
        viewModel.repoResults.observe(viewLifecycleOwner, Observer<List<Repo>> { repo ->
            repo?.apply {
                viewModelAdapter?.results = repo
                Log.i("Repo result: ", repo.get(1).name.toString())
                Log.i("Repo result: ", repo.get(1).html_url.toString())
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

    private fun searchRepo(searchTerm: String) {

    }
}