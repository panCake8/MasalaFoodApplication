package com.example.masalafoodapplication.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.Food
import com.example.masalafoodapplication.databinding.ItemFoodBinding
import com.example.masalafoodapplication.util.loadImage
import com.example.masalafoodapplication.util.setTime

class RecipesAdapter(
    private val recipes: List<Food>,
    private val listener: HomeInteractionListener
) :
    RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return RecipesViewHolder(view)
    }

    override fun getItemCount() = recipes.size

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.bind(currentRecipe)
    }

    inner class RecipesViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemFoodBinding.bind(viewItem)
        fun bind(food: Food) {
            binding.apply {
                recipeName.text = food.recipeName
                prepareTime.setTime(food.timeMinutes)
                imageRecipe.loadImage(food.imageUrl)
                root.setOnClickListener {
                    listener.onRecipeClicked(food)
                }
            }
        }
    }

}