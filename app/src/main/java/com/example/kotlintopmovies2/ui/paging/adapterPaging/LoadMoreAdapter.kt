package com.example.kotlintopmovies2.ui.paging.adapterPaging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintopmovies2.databinding.LoadMoreBinding

class LoadMoreAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadMoreAdapter.MyViewHolder>() {

    private lateinit var binding: LoadMoreBinding

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadMoreAdapter.MyViewHolder {
        binding = LoadMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(retry)
    }

    override fun onBindViewHolder(holder: LoadMoreAdapter.MyViewHolder, loadState: LoadState) {
        holder.binData(loadState)
    }

    inner class MyViewHolder(private val retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.loadMoreRetry.setOnClickListener { retry() }
        }

        fun binData(state: LoadState) {
            binding.apply {
                loadMoreProgress.isVisible = state is LoadState.Loading
                loadMoreTxt.isVisible = state is LoadState.Error
                loadMoreRetry.isVisible = state is LoadState.Error
            }
        }
    }
}