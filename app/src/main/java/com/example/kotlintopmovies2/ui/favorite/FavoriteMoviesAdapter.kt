package com.example.kotlintopmovies2.ui.favorite

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlintopmovies2.data.db.MoviesEntity
import com.example.kotlintopmovies2.databinding.ItemPagingMoviesLastBinding

class FavoriteMoviesAdapter : RecyclerView.Adapter<FavoriteMoviesAdapter.MyViewHolder>() {

    private lateinit var binding: ItemPagingMoviesLastBinding
    private var moviesList = emptyList<MoviesEntity>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemPagingMoviesLastBinding.inflate(inflater, parent, false)
        context = parent.context
        return MyViewHolder()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(moviesList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = moviesList.size

    inner class MyViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: MoviesEntity) {
            binding.apply {
                moviesNameTxt.text = item.title
                moviesRateTxt.text = item.rate
                moviesCountryTxt.text = item.country
                moviesYearTxt.text = item.year
                Glide.with(context).load(item.poster).into(moviesPosterImg)
/*                moviesPosterImg.load(item.poster) {
                    crossfade(true)
                    crossfade(800)
                }*/

                //Click
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((MoviesEntity) -> Unit)? = null
    fun setOnItemClickListener(listener: (MoviesEntity) -> Unit) {
        onItemClickListener = listener
    }

    fun setDataDiffer(data: List<MoviesEntity>) {

        val moviesDiffUtils = MoviesDiffUtils(moviesList, data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtils)
        moviesList = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class MoviesDiffUtils(
        private val oldItem: List<MoviesEntity>,
        private val newItem: List<MoviesEntity>,
    ) : DiffUtil.Callback() {
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