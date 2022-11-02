package com.example.kotlintopmovies2.utils

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.initRecycler(
    layoutManager: RecyclerView.LayoutManager,
    adapter: RecyclerView.Adapter<*>,
) {
    this.layoutManager = layoutManager
    this.adapter = adapter
}