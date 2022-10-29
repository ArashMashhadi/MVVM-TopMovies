package com.example.kotlintopmovies2.ui.paging.adapterPaging

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlintopmovies2.databinding.ItemPagingMoviesLastBinding
import com.example.kotlintopmovies2.data.model.home.ResponseMoviesList
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MoviesPagingAdapter @Inject constructor(@ApplicationContext private val context: Context) :
    PagingDataAdapter<ResponseMoviesList.Data, MoviesPagingAdapter.MyViewHolder>(differCallBack) {

    private lateinit var binding: ItemPagingMoviesLastBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MoviesPagingAdapter.MyViewHolder {
        binding =
            ItemPagingMoviesLastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder()
    }

    override fun onBindViewHolder(holder: MoviesPagingAdapter.MyViewHolder, position: Int) {
        holder.bindData(getItem(position)!!)
        holder.setIsRecyclable(false)
    }

    inner class MyViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: ResponseMoviesList.Data) {
            binding.apply {
                moviesNameTxt.text = item.title
                moviesRateTxt.text = item.imdbRating
                moviesCountryTxt.text = item.country
                moviesYearTxt.text = item.year
                Glide.with(context).load(item.poster).into(moviesPosterImg)

                //Click
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((ResponseMoviesList.Data) -> Unit)? = null

    fun setOnItemClickListener(listener: (ResponseMoviesList.Data) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        val differCallBack = object : DiffUtil.ItemCallback<ResponseMoviesList.Data>() {
            override fun areItemsTheSame(
                oldItem: ResponseMoviesList.Data,
                newItem: ResponseMoviesList.Data,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResponseMoviesList.Data,
                newItem: ResponseMoviesList.Data,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}