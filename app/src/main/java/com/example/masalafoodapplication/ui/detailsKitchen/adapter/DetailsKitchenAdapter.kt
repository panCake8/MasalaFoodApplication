package com.example.masalafoodapplication.ui.detailsKitchen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.ItemListPopularDishesBinding
import com.example.masalafoodapplication.databinding.ItemPosterKetchenBinding
import com.example.masalafoodapplication.util.Constants

class DetailsKitchenAdapter(
    private val list: List<DetailsKitchenItem<Any>>
    ,private val listener:DetailsKitchenOnClick)
    :RecyclerView.Adapter<DetailsKitchenAdapter.BaseDetailsKitchenHolder>(),DetailsKitchenOnClick {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseDetailsKitchenHolder {
        return when (viewType) {
            R.layout.item_poster_ketchen -> PosterHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_poster_ketchen,
                    parent,
                    false
                )
            )

            R.layout.item_list_popular_dishes -> PopularDishesHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_list_popular_dishes,
                    parent,
                    false
                )
            )

            else -> throw Exception(Constants.UNKNOWN_HOME_ITEM_TYPE)
        }
    }

    override fun onBindViewHolder(holder: BaseDetailsKitchenHolder, position: Int) {
        when (holder) {
            is PosterHolder -> holder.bind(list[position])
            is PopularDishesHolder -> holder.bind(list[position])

        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position].type) {
            DetailsItemType.POSTER -> R.layout.item_poster_ketchen
            DetailsItemType.POPULAR_DISHES -> R.layout.item_list_popular_dishes
        }
    }
    override fun getItemCount() = list.size


    abstract class BaseDetailsKitchenHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        abstract fun bind(item:DetailsKitchenItem<Any>)
    }

    inner class PosterHolder(viewItem:View):BaseDetailsKitchenHolder(viewItem){
        val binding=ItemPosterKetchenBinding.bind(viewItem)
        override fun bind(item:DetailsKitchenItem<Any>) {
            binding.imagePoster.setImageResource(R.drawable.poster)
        }
    }

    inner class PopularDishesHolder(viewItem:View):BaseDetailsKitchenHolder(viewItem){
        val binding=ItemListPopularDishesBinding.bind(viewItem)
        override fun bind(item:DetailsKitchenItem<Any>) {
            val adapter=PopularDishesAdapter(item.data as List<Food>
                ,this@DetailsKitchenAdapter)
            binding.recyclerPopularDishes.adapter=adapter
        }
    }

    override fun onClickListener(food: Food) {
        listener.onClickListener(food)
    }
}