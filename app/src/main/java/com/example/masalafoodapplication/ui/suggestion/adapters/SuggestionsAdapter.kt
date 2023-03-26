package com.example.masalafoodapplication.ui.suggestion.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.databinding.ItemSuggestionsBinding
import com.example.masalafoodapplication.util.SuggestionOnClick
import com.example.masalafoodapplication.util.SuggestionsItems

class SuggestionsAdapter(private val list: List<SuggestionsItems>, private val listener:SuggestionOnClick)
    :RecyclerView.Adapter<SuggestionsAdapter.SuggestionsHolder>(),SuggestionOnClick {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionsHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_suggestions
        ,parent
        ,false
        )
        return SuggestionsHolder(view)
    }

    override fun onBindViewHolder(holder: SuggestionsHolder, position: Int) {
       val item=list[position]
        holder.binding.apply {
            itemTvTitle.text=item.title

            val adapter= FoodsAdapter(item.foods,this@SuggestionsAdapter)
            itemRvSuggestions.adapter=adapter
        }
    }
    override fun getItemCount()=list.size

    class SuggestionsHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val binding=ItemSuggestionsBinding.bind(itemView)
    }

    override fun onClickListener(nameFood: String) {
        listener.onClickListener(nameFood)
    }
}