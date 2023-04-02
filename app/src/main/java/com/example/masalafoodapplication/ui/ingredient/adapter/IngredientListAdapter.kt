package com.example.masalafoodapplication.ui.ingredient.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.ItemIngredientCheckboxBinding

class IngredientListAdapter(food: Food) :
    RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder>() {

    private val ingredient = food.ingredientQuantities
    private val checkedPositions = mutableSetOf<Int>()

    // other adapter methods...

    fun getCheckedCount(): Int {
        return checkedPositions.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ingredient_checkbox, parent, false)
        return IngredientViewHolder(view)
    }

    override fun getItemCount(): Int = ingredient.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.apply {
            binding.checkBox.text = "${position + 1}- ${ingredient[position]}"
            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkedPositions.add(position)
                } else {
                    checkedPositions.remove(position)
                }
            }
        }
    }

    class IngredientViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemIngredientCheckboxBinding.bind(viewItem)
    }
}