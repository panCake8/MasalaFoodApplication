package com.example.masalafoodapplication.ui.suggestion.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.databinding.ItemFoodSuggestionsBinding
import com.example.masalafoodapplication.util.loadImage
import com.example.masalafoodapplication.data.domain.models.Food

class FoodsAdapter(private val foods: List<Food>, private val listener: SuggestionOnClick) :
    RecyclerView.Adapter<FoodsAdapter.FoodsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_food_suggestions, parent, false
        )
        return FoodsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {
        val food = foods[position]
        holder.bind(food)
    }

    override fun getItemCount() = foods.size

    inner class FoodsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemFoodSuggestionsBinding.bind(itemView)
        fun bind(food:Food){
            binding.apply {
                imageRecipe.loadImage(food.imageUrl)
                textRecipeName.text = food.recipeName
                textPrepareTime.text = "${food.timeMinutes} m"
                root.setOnClickListener { listener.onClickListener(food) }
            }
        }
    }

}

interface SuggestionOnClick{
    fun onClickListener(food: Food)
}
