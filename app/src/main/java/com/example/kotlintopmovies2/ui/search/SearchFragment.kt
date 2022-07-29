package com.example.kotlintopmovies2.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlintopmovies2.databinding.FragmentSearchBinding
import com.example.kotlintopmovies2.ui.home.adapter.LastMoviesAdapter
import com.example.kotlintopmovies2.utils.initRecycler
import com.example.kotlintopmovies2.utils.showInvisible
import com.example.kotlintopmovies2.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentSearchBinding

    //ViewModel
    private val viewModel: SearchViewModel by viewModels()

    //Adapter
    private val searchAdapter: LastMoviesAdapter by lazy { LastMoviesAdapter(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //InitView
        binding.apply {

            //Search
            searchEdt.addTextChangedListener{
                val search = it.toString()
                if(search.isNotEmpty()){
                    viewModel.loadSearchMovise(search)
                }
            }
            //Get movies list
            viewModel.moviesList.observe(viewLifecycleOwner){
                searchAdapter.setDataDiffer(it.data)
                moviesRecycler.initRecycler(LinearLayoutManager(requireContext()),searchAdapter)
            }

            //Click
            searchAdapter.setOnItemClickListener {
                val direction = SearchFragmentDirections.actionToDetail(it.id!!.toInt())
                findNavController().navigate(direction)
            }

            //Loading
            viewModel.loading.observe(viewLifecycleOwner){
                if(it){
                    searchLoading.showInvisible(true)
                }else{
                    searchLoading.showInvisible(false)
                }
            }
            //EmptyItems
            viewModel.empty.observe(viewLifecycleOwner){
                if(it){
                    emptyItemsLay.showInvisible(true)
                    moviesRecycler.showInvisible(false)
                }else{
                    emptyItemsLay.showInvisible(false)
                    moviesRecycler.showInvisible(true)
                }
            }
        }
    }
}