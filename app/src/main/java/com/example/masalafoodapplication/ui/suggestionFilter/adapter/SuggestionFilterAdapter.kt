package com.example.masalafoodapplication.ui.suggestionFilter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.databinding.ItemIngredientChipsBinding

class SuggestionFilterAdapter(val list: MutableList<String>, val listener: SuggestionFilterInteractionListener)  : RecyclerView.Adapter<SuggestionFilterAdapter.IngredientViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient_chips,parent,false)
        return IngredientViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val currentIngredient = list[position]
        holder.binding.apply {
            chipFilter.text = currentIngredient
            chipFilter.setOnCheckedChangeListener { _, b -> listener.onChipClicks(currentIngredient,b) }
        }
    }

    class IngredientViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemIngredientChipsBinding.bind(viewItem)
    }
}