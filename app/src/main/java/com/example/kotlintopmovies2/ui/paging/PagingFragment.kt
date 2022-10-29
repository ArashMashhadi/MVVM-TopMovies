package com.example.kotlintopmovies2.ui.paging

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlintopmovies2.databinding.FragmentPagingBinding
import com.example.kotlintopmovies2.ui.paging.adapterPaging.LoadMoreAdapter
import com.example.kotlintopmovies2.ui.paging.adapterPaging.MoviesPagingAdapter
import com.example.kotlintopmovies2.viewmodel.PagingViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PagingFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentPagingBinding

    //ViewModel
    private val viewModel: PagingViewModel by viewModels()

    //Adapter Paging
    private val moviesPagingAdapter: MoviesPagingAdapter by lazy {
        MoviesPagingAdapter(
            requireContext()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //InitView
        binding.apply {

            //Load Data
            lifecycleScope.launchWhenCreated {
                Log.i("aaa", "paging ${Thread.currentThread().name}")
                viewModel.moviesLit.collect {
                    moviesPagingAdapter.submitData(it)
                }
            }
            //Loading
            lifecycleScope.launch(Dispatchers.IO) {
                Log.i("aaa", "paging1 ${Thread.currentThread().name}")
                moviesPagingAdapter.loadStateFlow.collect {
                    val state = it.refresh
                    movieLoading.isVisible = state is LoadState.Loading
                }
            }
            //RecyclerView
            recycler.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = moviesPagingAdapter
            }

            //SwipeRefresh
            movieSwipe.setOnRefreshListener {
                movieSwipe.isRefreshing = false
                moviesPagingAdapter.refresh()
            }
            //LoadMore
            recycler.adapter = moviesPagingAdapter.withLoadStateFooter(
                LoadMoreAdapter { moviesPagingAdapter.retry() }
            )

            //Click
            moviesPagingAdapter.setOnItemClickListener {
                val direction = PagingFragmentDirections.pagingToDetail(it.id!!.toInt())
                findNavController().navigate(direction)
            }
        }
    }
}