package com.example.kotlintopmovies2.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlintopmovies2.databinding.ItemPagingMoviesLastBinding
import com.example.kotlintopmovies2.data.model.home.ResponseMoviesList
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LastMoviesAdapter @Inject constructor(@ApplicationContext private val context : Context) : RecyclerView.Adapter<LastMoviesAdapter.MyViewHolder>() {

    private lateinit var binding : ItemPagingMoviesLastBinding
    private var moviesList = emptyList<ResponseMoviesList.Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastMoviesAdapter.MyViewHolder {

    binding = ItemPagingMoviesLastBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    return MyViewHolder()
    }

    override fun onBindViewHolder(holder: LastMoviesAdapter.MyViewHolder, position: Int) {
        holder.bindData(moviesList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = moviesList.size

    inner class  MyViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun bindData(item : ResponseMoviesList.Data){
            binding.apply {
                moviesNameTxt.text = item.title
                moviesRateTxt.text = item.imdbRating
                moviesCountryTxt.text = item.country
                moviesYearTxt.text = item.year
                Glide.with(context).load(item.poster).into(moviesPosterImg)

                //Click
                root.setOnClickListener{
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }

    }

    private var onItemClickListener : ((ResponseMoviesList.Data) -> Unit)? = null

    fun setOnItemClickListener(listener : (ResponseMoviesList.Data) -> Unit){
        onItemClickListener = listener
    }

    fun setDataDiffer(data : List<ResponseMoviesList.Data>) {
        val moviesDiffUtil = MoviesDiffUtils(moviesList,data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtil)
        moviesList = data
        diffUtils.dispatchUpdatesTo(this)

    }

    class MoviesDiffUtils(private val oldItem : List<ResponseMoviesList.Data> , private val newItem : List<ResponseMoviesList.Data>) :DiffUtil.Callback(){
        override fun getOldListSize(): Int {
        return oldItem.size
        }

        override fun getNewListSize(): Int {
        return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

    }
}