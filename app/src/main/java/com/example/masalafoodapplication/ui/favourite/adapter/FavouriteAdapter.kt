package com.example.masalafoodapplication.ui.favourite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.FavoritItemCardBinding
import com.example.masalafoodapplication.util.loadImage

class FavouriteAdapter(var list: List<Food>, val listener: FavouriteListener) :
    RecyclerView.Adapter<FavouriteAdapter.FavouriteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteHolder {
        val binding =
            FavoritItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteHolder, position: Int) {
        val currentFood = list[position]
        holder.binding.apply {
            imvCity1.loadImage(currentFood.imageUrl)
            txvCityName1.text = currentFood.recipeName
            timeTv1.text = currentFood.timeMinutes.toString()
            imageView1.setOnClickListener {
                listener.onClickHeart(position)
            }
        }
    }

    override fun getItemCount() = list.size

    fun setData(newList: List<Food>) {
        val diffResult = DiffUtil.calculateDiff(FavouriteDiffUtil(list, newList))
        list = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class FavouriteHolder(val binding: FavoritItemCardBinding) :
        RecyclerView.ViewHolder(binding.root)
}