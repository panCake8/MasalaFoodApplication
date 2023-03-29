package com.example.masalafoodapplication.ui.suggestion.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.ItemSuggestionsBinding
import com.example.masalafoodapplication.ui.suggestion.SuggestionsItems

class SuggestionsAdapter(
    private val list: List<SuggestionsItems>,
    private val listener: SuggestionOnClick
) : RecyclerView.Adapter<SuggestionsAdapter.SuggestionsHolder>(), SuggestionOnClick {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionsHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_suggestions, parent, false
        )
        return SuggestionsHolder(view)
    }

    override fun onBindViewHolder(holder: SuggestionsHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount() = list.size

    inner class SuggestionsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemSuggestionsBinding.bind(itemView)
        fun bind(item:SuggestionsItems){
            binding.apply {
                textTitleMeal.text = item.title
                val adapter = FoodsAdapter(item.foods, this@SuggestionsAdapter)
                recyclerSuggestionsMeal.adapter = adapter

                if (item.foods.isEmpty()){
                    binding.recyclerSuggestionsMeal.visibility=View.GONE
                    binding.textError.visibility=View.VISIBLE
                }else{
                    binding.recyclerSuggestionsMeal.visibility=View.VISIBLE
                    binding.textError.visibility=View.GONE
                }
            }
        }
    }
    override fun onClickListener(food:Food) {
        listener.onClickListener(food)
    }

}