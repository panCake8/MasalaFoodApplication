package com.example.masalafoodapplication.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.masalafoodapplication.databinding.ItemBannerBinding
import com.example.masalafoodapplication.databinding.ItemHistoryBinding
import com.example.masalafoodapplication.databinding.ListCuisinesBinding
import com.example.masalafoodapplication.databinding.ListJustForYouBinding
import com.example.masalafoodapplication.databinding.ListQuickRecipesBinding
import com.example.masalafoodapplication.ui.base.BaseAdapter2
import com.example.masalafoodapplication.ui.home.HomeInteractionListener
import com.example.masalafoodapplication.ui.home.HomeItem
import com.example.masalafoodapplication.util.Constants.UNKNOWN_HOME_ITEM_TYPE
import com.example.masalafoodapplication.util.loadImage

class HomeAdapter(
    private var items: List<HomeItem>,
    private val listener: HomeInteractionListener,
) : BaseAdapter2<HomeItem, ViewBinding>(items) {

    companion object {
        const val BANNER = 0
        const val QUICK_RECIPES = 1
        const val CUISINES = 2
        const val JUST_FOR_YOU = 3
        const val INDIAN_FOOD_HISTORY = 4
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewBinding {
        return when (viewType) {
            BANNER -> ItemBannerBinding.inflate(inflater, parent, false)
            QUICK_RECIPES -> ListQuickRecipesBinding.inflate(inflater, parent, false)
            CUISINES -> ListCuisinesBinding.inflate(inflater, parent, false)
            JUST_FOR_YOU -> ListJustForYouBinding.inflate(inflater, parent, false)
            INDIAN_FOOD_HISTORY -> ItemHistoryBinding.inflate(inflater, parent, false)
            else -> throw IllegalArgumentException(UNKNOWN_HOME_ITEM_TYPE)
        }
    }

    override fun bind(binding: ViewBinding, item: HomeItem) {
        when (binding) {
            is ItemBannerBinding -> bindBanner(binding, item as HomeItem.Banner)
            is ListQuickRecipesBinding -> bindQuickRecipes(binding, item as HomeItem.QuickRecipes)
            is ListCuisinesBinding -> bindCuisines(binding, item as HomeItem.Cuisines)
            is ListJustForYouBinding -> bindJustForYou(binding, item as HomeItem.JustForYou)
            is ItemHistoryBinding -> bindIndianFoodHistory(binding, item as HomeItem.FoodHistory)
            else -> throw IllegalArgumentException(UNKNOWN_HOME_ITEM_TYPE)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is HomeItem.Banner -> BANNER
            is HomeItem.QuickRecipes -> QUICK_RECIPES
            is HomeItem.Cuisines -> CUISINES
            is HomeItem.JustForYou -> JUST_FOR_YOU
            is HomeItem.FoodHistory -> INDIAN_FOOD_HISTORY
        }
    }

    private fun bindBanner(binding: ItemBannerBinding, item: HomeItem.Banner) {
        binding.apply {
            imageBanner.loadImage(item.data)
            root.setOnClickListener { listener.onBannerClicked() }
        }
    }

    private fun bindQuickRecipes(binding: ListQuickRecipesBinding, item: HomeItem.QuickRecipes) {
        binding.apply {
            recyclerRecipes.adapter = RecipesAdapter(item.data, listener)
            buttonSeeMore.setOnClickListener { listener.onSeeMoreClicked(item.type) }
        }
    }

    private fun bindCuisines(binding: ListCuisinesBinding, item: HomeItem.Cuisines) {
        binding.recyclerCuisines.adapter = CuisinesAdapter(item.data, listener)
    }

    private fun bindJustForYou(binding: ListJustForYouBinding, item: HomeItem.JustForYou) {
        binding.apply {
            recyclerRecipes.adapter = RecipesAdapter(item.data, listener)
            buttonSeeMore.setOnClickListener { listener.onSeeMoreClicked(item.type) }
        }
    }

    private fun bindIndianFoodHistory(binding: ItemHistoryBinding, item: HomeItem.FoodHistory) {
        binding.apply {
            imageFoodHistory.loadImage(item.data)
            root.setOnClickListener { listener.onIndianFoodHistoryClicked() }
        }
    }
}