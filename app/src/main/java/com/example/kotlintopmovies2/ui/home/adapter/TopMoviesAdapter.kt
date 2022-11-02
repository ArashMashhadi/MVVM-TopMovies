package com.example.kotlintopmovies2.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlintopmovies2.data.model.home.ResponseMoviesList
import com.example.kotlintopmovies2.databinding.ItemHomeMoviesTopBinding

class TopMoviesAdapter :
    RecyclerView.Adapter<TopMoviesAdapter.MyViewHolder>() {

    private lateinit var binding: ItemHomeMoviesTopBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding =
            ItemHomeMoviesTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return MyViewHolder()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = if (differ.currentList.size > 10) 10 else differ.currentList.size

    inner class MyViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: ResponseMoviesList.Data) {
            binding.apply {
                moviesNameTxt.text = item.title
                moviesInfoTxt.text = "${item.imdbRating} | ${item.country} | ${item.year}"

                Glide.with(context).load(item.poster).into(moviesPosterImg)
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<ResponseMoviesList.Data>() {
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

    val differ = AsyncListDiffer(this, differCallBack)
}