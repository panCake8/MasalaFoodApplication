package com.example.masalafoodapplication.ui.ingredient.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.ItemStepIngredientBinding

class IngredientListAdapter(food: Food) :
    RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder>() {

    private val ingredient = food.ingredientQuantities
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_step_ingredient, parent, false)
        return IngredientViewHolder(view)
    }

    override fun getItemCount(): Int = ingredient.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.apply {
            binding.checkBox.text = "${position + 1}- ${ingredient[position]}"
        }
    }

    class IngredientViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemStepIngredientBinding.bind(viewItem)
    }
}