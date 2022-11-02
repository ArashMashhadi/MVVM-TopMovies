package com.example.kotlintopmovies2.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kotlintopmovies2.databinding.ItemDetailImagesBinding

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.MyViewHolder>() {

    private lateinit var binding: ItemDetailImagesBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemDetailImagesBinding.inflate(inflater, parent, false)
        return MyViewHolder()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class MyViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: String) {
            binding.apply {
                itemImages.load(item) {
                    crossfade(true)
                    crossfade(800)
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
}