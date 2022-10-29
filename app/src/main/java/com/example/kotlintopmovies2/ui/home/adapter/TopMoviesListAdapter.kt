package com.example.kotlintopmovies2.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlintopmovies2.databinding.ItemHomeMoviesTopBinding
import com.example.kotlintopmovies2.data.model.home.ResponseMoviesList

class TopMoviesListAdapter:ListAdapter<ResponseMoviesList.Data,TopMoviesListAdapter.MyViewHolder>(ListMovieDiffUtil) {

    private lateinit var binding: ItemHomeMoviesTopBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemHomeMoviesTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return MyViewHolder(binding,context)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(currentList[position])
    }

     class MyViewHolder (private var binding: ItemHomeMoviesTopBinding,private var context: Context): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: ResponseMoviesList.Data) {
            binding.apply {
                moviesNameTxt.text = item.title
                moviesInfoTxt.text = "${item.imdbRating} | ${item.country} | ${item.year}"

                Glide.with(context).load(item.poster).into(moviesPosterImg)
            }
        }
    }
}