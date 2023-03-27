package com.example.masalafoodapplication.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.models.Cuisine
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.data.domain.models.HomeItem
import com.example.masalafoodapplication.data.domain.enums.HomeItemType
import com.example.masalafoodapplication.databinding.ItemBannerBinding
import com.example.masalafoodapplication.databinding.ItemHistoryBinding
import com.example.masalafoodapplication.databinding.ListCuisinesBinding
import com.example.masalafoodapplication.databinding.ListJustForYouBinding
import com.example.masalafoodapplication.databinding.ListRecipesBinding
import com.example.masalafoodapplication.util.Constants.UNKNOWN_HOME_ITEM_TYPE
import com.example.masalafoodapplication.util.loadImage

class HomeAdapter(
    private val homeItems: List<HomeItem<Any>>,
    private val listener: HomeInteractionListener
) :
    RecyclerView.Adapter<HomeAdapter.BaseHomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHomeViewHolder {
        return when (viewType) {
            R.layout.item_banner -> BannerViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_banner,
                    parent,
                    false
                )
            )

            R.layout.list_recipes -> QuickRecipesViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_recipes,
                    parent,
                    false
                )
            )

            R.layout.list_cuisines -> CuisinesViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_cuisines,
                    parent,
                    false
                )
            )

            R.layout.item_history -> IndianFoodHistoryViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_history,
                    parent,
                    false
                )
            )

            R.layout.list_just_for_you ->JustForYouRecipesViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_just_for_you,
                    parent,
                    false
                ))

            else -> throw Exception(UNKNOWN_HOME_ITEM_TYPE)
        }
    }

    override fun onBindViewHolder(holder: BaseHomeViewHolder, position: Int) {
        when (holder) {
            is BannerViewHolder -> holder.bind(homeItems[position])
            is QuickRecipesViewHolder -> holder.bind(homeItems[position])
            is JustForYouRecipesViewHolder -> holder.bind(homeItems[position])
            is CuisinesViewHolder -> holder.bind(homeItems[position])
            is IndianFoodHistoryViewHolder -> holder.bind(homeItems[position])
        }
    }

    override fun getItemCount() = homeItems.size

    override fun getItemViewType(position: Int): Int {
        return when (homeItems[position].type) {
            HomeItemType.BANNER -> R.layout.item_banner
            HomeItemType.CUISINES -> R.layout.list_cuisines
            HomeItemType.INDIAN_FOOD_HISTORY -> R.layout.item_history
            HomeItemType.QUICK_RECIPES -> R.layout.list_recipes
            HomeItemType.JUST_FOR_YOU -> R.layout.list_just_for_you

        }
    }

    abstract class BaseHomeViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        abstract fun bind(item: HomeItem<Any>)
    }

    inner class BannerViewHolder(viewItem: View) : BaseHomeViewHolder(viewItem) {
        private val binding = ItemBannerBinding.bind(viewItem)

        override fun bind(item: HomeItem<Any>) {
            binding.imageBanner.loadImage(item.data as String)
        }
    }

    inner class QuickRecipesViewHolder(viewItem: View) : BaseHomeViewHolder(viewItem) {
        private val binding = ListRecipesBinding.bind(viewItem)

        override fun bind(item: HomeItem<Any>) {
            binding.apply {
                labelSection.text = itemView.context.getString(R.string.quick_recipes)
                recyclerRecipes.adapter = QuickRecipesAdapter(item.data as List<Food>, listener)
                buttonSeeMore.setOnClickListener { listener.onSeeMoreClicked(item.type) }
            }
        }
    }

    inner class JustForYouRecipesViewHolder(viewItem: View) : BaseHomeViewHolder(viewItem) {
        private val binding = ListJustForYouBinding.bind(viewItem)

        override fun bind(item: HomeItem<Any>) {
            binding.apply {
                labelSection.text = itemView.context.getString(R.string.just_for_you)
                buttonSeeMore.setOnClickListener { listener.onSeeMoreClicked(item.type) }
                recyclerRecipes.adapter = JustForYouAdapter(item.data as List<Food>,listener)
            }
        }
    }

    inner class CuisinesViewHolder(viewItem: View) : BaseHomeViewHolder(viewItem) {
        private val binding = ListCuisinesBinding.bind(viewItem)

        override fun bind(item: HomeItem<Any>) {
            binding.apply {
                labelSection.text = itemView.context.getString(R.string.cuisines)
                recyclerCuisines.adapter = CuisinesAdapter(item.data as List<Cuisine>, listener)
                root.setOnClickListener { listener.onSeeMoreClicked(item.type) }
            }
        }
    }

    inner class IndianFoodHistoryViewHolder(viewItem: View) : BaseHomeViewHolder(viewItem) {
        private val binding = ItemHistoryBinding.bind(viewItem)

        override fun bind(item: HomeItem<Any>) {
            binding.apply {
                labelFoodHistory.text = itemView.context.getString(R.string.indian_food_history)
                imageFoodHistory.loadImage(item.data as String)
                root.setOnClickListener { listener.onIndianFoodHistoryClicked() }
            }
        }
    }

}