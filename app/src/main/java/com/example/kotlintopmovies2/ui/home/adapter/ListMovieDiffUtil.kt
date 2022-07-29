package com.example.kotlintopmovies2.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.kotlintopmovies2.model.home.ResponseMoviesList

object ListMovieDiffUtil : DiffUtil.ItemCallback<ResponseMoviesList.Data>() {
    override fun areItemsTheSame(
        oldItem: ResponseMoviesList.Data,
        newItem: ResponseMoviesList.Data
    ): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(
        oldItem: ResponseMoviesList.Data,
        newItem: ResponseMoviesList.Data
    ): Boolean =
        areItemsTheSame(oldItem, newItem)
}