package com.example.kotlintopmovies2.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintopmovies2.databinding.ItemHomeGenreListBinding
import com.example.kotlintopmovies2.data.model.home.ResponseGenresList

class GenresAdapter : RecyclerView.Adapter<GenresAdapter.MyViewHolder>() {

    private lateinit var binding: ItemHomeGenreListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresAdapter.MyViewHolder {
        binding =
            ItemHomeGenreListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder()
    }

    override fun onBindViewHolder(holder: GenresAdapter.MyViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class MyViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun setData(item: ResponseGenresList.ResponseGenresListItem) {
            binding.apply {
                nameTxt.text = item.name
            }
        }
    }

    private val differCallBack =
        object : DiffUtil.ItemCallback<ResponseGenresList.ResponseGenresListItem>() {
            override fun areItemsTheSame(
                oldItem: ResponseGenresList.ResponseGenresListItem,
                newItem: ResponseGenresList.ResponseGenresListItem,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResponseGenresList.ResponseGenresListItem,
                newItem: ResponseGenresList.ResponseGenresListItem,
            ): Boolean {
                return oldItem == newItem
            }
        }

    val differ = AsyncListDiffer(this, differCallBack)
}