package com.example.masalafoodapplication.ui.quick_recipes.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.ItemRecipeCardBinding
import com.example.masalafoodapplication.util.loadImage

class QuickRecipesAdapter(val list: List<Food>, val listener: QuickRecipesInteractionListener) :
    RecyclerView.Adapter<QuickRecipesAdapter.RecipesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe_card, parent, false)
        return RecipesViewHolder(view)

    }

    override fun getItemCount() = list.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val currentRecipe = list[position]
        holder.binding.apply {
            textRecipeName.text = currentRecipe.recipeName
            textPreparationTime.text = currentRecipe.timeMinutes.toString() + "m"
            imageRecipe.loadImage(currentRecipe.imageUrl)
            root.setOnClickListener {
                listener.onClickRecipesCard(currentRecipe)
            }
        }

    }

    class RecipesViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemRecipeCardBinding.bind(viewItem)
    }


}