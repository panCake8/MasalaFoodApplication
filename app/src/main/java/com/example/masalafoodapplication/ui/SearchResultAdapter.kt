package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.*
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.util.loadImage
import com.example.masalafoodapplication.data.domain.Food

class SearchResultAdapter(var list: List<Food>) :
    RecyclerView.Adapter<SearchResultAdapter.ExploreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_resulte, parent, false)
        return ExploreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        val currentFood = list[position]
        holder.apply {
            imageRecipe.loadImage(currentFood.imageUrl)
            recipeName.text = currentFood.recipeName
            prepareTime.text = currentFood.timeMinutes.toString()
        }

    }

    override fun getItemCount() = list.size


    class ExploreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageRecipe: ImageView = itemView.findViewById(R.id.image_recipe)
        val recipeName: TextView = itemView.findViewById(R.id.recipe_name)
        val prepareTime: TextView = itemView.findViewById(R.id.prepareTime)
    }
    fun setData(newList: List<Food>){
        val diffResult = DiffUtil.calculateDiff(ExploreDiffUtil(list,newList))
        list = newList
        diffResult.dispatchUpdatesTo(this)
    }
}
