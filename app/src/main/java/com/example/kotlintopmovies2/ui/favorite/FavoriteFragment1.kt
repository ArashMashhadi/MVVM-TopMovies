package com.example.kotlintopmovies2.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlintopmovies2.databinding.FragmentFavoriteBinding
import com.example.kotlintopmovies2.utils.initRecycler
import com.example.kotlintopmovies2.utils.showInvisible
import com.example.kotlintopmovies2.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment1 : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteAdapter: FavoriteMoviesAdapter by lazy { FavoriteMoviesAdapter(requireContext()) }

    //Other
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitView
        binding.apply {

            //Show all favorite
            viewModel.loadFavoriteList()
            //List
            viewModel.favoriteList.observe(viewLifecycleOwner) {
                favoriteAdapter.setDataDiffer(it)
                favoriteRecycler.initRecycler(LinearLayoutManager(requireContext()), favoriteAdapter)
            }

            //Click
            favoriteAdapter.setOnItemClickListener {
                val direction = FavoriteFragment1Directions.actionToDetail(it.id)
                findNavController().navigate(direction)
            }

            //Empty
            viewModel.empty.observe(viewLifecycleOwner) {
                if (it) {
                    emptyItemsLay.showInvisible(true)
                    favoriteRecycler.showInvisible(false)
                } else {
                    emptyItemsLay.showInvisible(false)
                    favoriteRecycler.showInvisible(true)
                }
            }
        }
    }
}