package com.example.kotlintopmovies2.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.kotlintopmovies2.R
import com.example.kotlintopmovies2.databinding.FragmentDetailBinding
import com.example.kotlintopmovies2.data.db.MoviesEntity
import com.example.kotlintopmovies2.utils.initRecycler
import com.example.kotlintopmovies2.utils.showInvisible
import com.example.kotlintopmovies2.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val imagesAdapter: ImagesAdapter by lazy { ImagesAdapter() }

    @Inject
    lateinit var entity: MoviesEntity

    //Other
    private var movieId = 0
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Get Id
        movieId = args.moviesId
        //Call Api
        if (movieId > 0) {
            viewModel.loadDetailMovies(movieId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Load data
        loadData()
        //Loading
        loading()
        //Fav Icon
        favIcon()
        //Back
        back()
    }

    private fun loadData() {
        binding.apply {
            viewModel.detailMovie.observe(viewLifecycleOwner) { response ->
                posterBigImg.load(response.poster)
                posterNormalImg.load(response.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                moviesNameTxt.text = response.title
                moviesRateTxt.text = response.imdbRating
                moviesTimeTxt.text = response.runtime
                moviesDateTxt.text = response.released
                moviesSummaryInfo.text = response.plot
                moviesActorsInfo.text = response.actors
                //Images Adapter
                imagesAdapter.differ.submitList(response.images)
                imagesRecyclerView.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    imagesAdapter
                )
                //Fav Click
                favImg.setOnClickListener {
                    entity.id = movieId
                    entity.poster = response.poster.toString()
                    entity.title = response.title.toString()
                    entity.rate = response.rated.toString()
                    entity.country = response.country.toString()
                    entity.year = response.year.toString()
                    viewModel.favoriteMovies(movieId, entity)
                }
            }
        }
    }

    private fun loading() {
        binding.apply {
            viewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    detailLoading.showInvisible(true)
                    detailScrollView.showInvisible(false)
                } else {
                    detailLoading.showInvisible(false)
                    detailScrollView.showInvisible(true)
                }
            }
        }
    }

    private fun favIcon() {
        binding.apply {
            //Default fav icon color
            lifecycleScope.launch(Dispatchers.Main) {
                if (viewModel.existsMovies(movieId)) {
                    favImg.setColorFilter(ContextCompat.getColor(requireContext(), R.color.scarlet))
                } else {
                    favImg.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.black
                        )
                    )
                }
            }
            //Change Images with click
            viewModel.isFavorite.observe(viewLifecycleOwner) {
                if (it) {
                    favImg.setColorFilter(ContextCompat.getColor(requireContext(), R.color.scarlet))
                } else {
                    favImg.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.black
                        )
                    )
                }
            }
        }
    }

    private fun back() {
        binding.backImg.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}