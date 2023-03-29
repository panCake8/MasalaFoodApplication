package com.example.masalafoodapplication.ui.detailsKitchen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.ItemFoodSuggestionsBinding
import com.example.masalafoodapplication.util.loadImage
import com.example.masalafoodapplication.util.setTime

class PopularDishesAdapter(
    private val foods: List<Food>,
    private val listener: DetailsKitchenOnClick
) : RecyclerView.Adapter<PopularDishesAdapter.DetailsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_popular_dishes, parent, false
        )
        return DetailsHolder(view)
    }

    override fun onBindViewHolder(holder: DetailsHolder, position: Int) {
        val food = foods[position]
        holder.bind(food)
    }

    override fun getItemCount() = foods.size

    inner class DetailsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemFoodSuggestionsBinding.bind(itemView)

        fun bind(food: Food){
            binding.apply {
                imageRecipe.loadImage(food.imageUrl)
                textRecipeName.text = food.recipeName
                textPrepareTime.text = food.timeMinutes.setTime()
                root.setOnClickListener { listener.onClickListener(food) }
            }
        }
    }
}

interface DetailsKitchenOnClick {
    fun onClickListener(food: Food)
}