package com.example.kotlintopmovies2.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.kotlintopmovies2.databinding.FragmentHomeBinding
import com.example.kotlintopmovies2.ui.home.adapter.GenresAdapter
import com.example.kotlintopmovies2.ui.home.adapter.LastMoviesAdapter
import com.example.kotlintopmovies2.ui.home.adapter.TopMoviesAdapter
import com.example.kotlintopmovies2.ui.home.adapter.TopMoviesListAdapter
import com.example.kotlintopmovies2.utils.initRecycler
import com.example.kotlintopmovies2.utils.showInvisible
import com.example.kotlintopmovies2.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    //Binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    //Adapter
    //ListAdapter ********
    private val topMoviesListAdapter: TopMoviesListAdapter by lazy { TopMoviesListAdapter() }

    //RecyclerViewAdapter
    private val topMoviesAdapter: TopMoviesAdapter by lazy { TopMoviesAdapter() }
    private val genresAdapter: GenresAdapter by lazy { GenresAdapter() }
    private val lastMoviesAdapter: LastMoviesAdapter by lazy { LastMoviesAdapter() }

    //ViewModel
    private val viewModel: HomeViewModel by viewModels()

    //PagerHelper
    private val pagerHelper: PagerSnapHelper by lazy { PagerSnapHelper() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Call Api
        viewModel.loadTopMoviesList(1)
        viewModel.loadGenresList()
        viewModel.loadLastMoviesList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Get Top Movies
        getTopMovies()
        //Get Genres
        getGenresMovies()
        //Get Last Movies
        getLastMovies()
        //Click
        clickListenerAdapter()
        //Loading
        loading()
    }

    private fun getTopMovies() {
        binding.apply {
            viewModel.topMoviesList.observe(viewLifecycleOwner) {
                topMoviesAdapter.differ.submitList(it.data)

                //RecyclerView Top Movies
                topMoviesRecycler.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    topMoviesAdapter)

                //Indicator
                pagerHelper.attachToRecyclerView(topMoviesRecycler)
                topMoviesIndicator.attachToRecyclerView(topMoviesRecycler, pagerHelper)
            }
        }
    }

    private fun getGenresMovies() {
        binding.apply {
            viewModel.genresList.observe(viewLifecycleOwner) {
                genresAdapter.differ.submitList(it)

                //RecyclerView Genres
                genresRecycler.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    genresAdapter
                )
            }
        }
    }

    private fun getLastMovies() {
        binding.apply {
            viewModel.lastMoviesList.observe(viewLifecycleOwner) {
                lastMoviesAdapter.setDataDiffer(it.data)

                //RecyclerView Last Movies
                lastMoviesRecycler.initRecycler(
                    LinearLayoutManager(requireContext()), lastMoviesAdapter
                )
            }
        }
    }

    private fun clickListenerAdapter() {
        lastMoviesAdapter.setOnItemClickListener {
            val direction = HomeFragmentDirections.actionToDetail(it.id!!.toInt())
            findNavController().navigate(direction)
        }
    }

    private fun loading() {
        binding.apply {
            viewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    moviesLoading.showInvisible(true)
                    moviesScrollLay.showInvisible(false)
                } else {
                    moviesLoading.showInvisible(false)
                    moviesScrollLay.showInvisible(true)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}