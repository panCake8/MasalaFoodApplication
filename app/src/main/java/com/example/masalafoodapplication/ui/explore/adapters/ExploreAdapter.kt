package com.example.masalafoodapplication.ui.explore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.util.loadImage
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.ItemSearchResulteBinding

class ExploreAdapter(var list: List<Food>) :
    RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        val binding =
            ItemSearchResulteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExploreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        val currentFood = list[position]
        holder.binding.apply {
            imageRecipe.loadImage(currentFood.imageUrl)
            recipeName.text = currentFood.recipeName
            prepareTime.text = currentFood.timeMinutes.toString()
        }

    }

    override fun getItemCount() = list.size


    class ExploreViewHolder(val binding: ItemSearchResulteBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setData(newList: List<Food>) {
        val diffResult = DiffUtil.calculateDiff(ExploreDiffUtil(list, newList))
        list = newList
        diffResult.dispatchUpdatesTo(this)
    }
}
