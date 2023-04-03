package com.example.masalafoodapplication.ui.explore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.util.loadImage
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.ItemSearchResultBinding


class ExploreAdapter(var list: List<Food>, val listener: ExploreListener) :
    RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        val binding =
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExploreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        val currentFood = list[position]
        holder.binding.apply {
            imageRecipe.loadImage(currentFood.imageUrl)
            textRecipeName.text = currentFood.recipeName
            textPreparationTime.text =currentFood.timeMinutes.toString() + "m"
            root.setOnClickListener {
                listener.onClickItem(currentFood)
            }
        }

    }

    override fun getItemCount() = list.size


    class ExploreViewHolder(val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setData(newList: List<Food>) {
        val diffResult = DiffUtil.calculateDiff(ExploreDiffUtil(list, newList))
        list = newList
        diffResult.dispatchUpdatesTo(this)
    }
}
