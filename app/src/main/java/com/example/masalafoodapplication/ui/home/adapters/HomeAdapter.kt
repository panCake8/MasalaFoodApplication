package com.example.masalafoodapplication.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.masalafoodapplication.databinding.ItemBannerBinding
import com.example.masalafoodapplication.databinding.ItemHistoryBinding
import com.example.masalafoodapplication.databinding.ListCuisinesBinding
import com.example.masalafoodapplication.databinding.ListQuickRecipesBinding
import com.example.masalafoodapplication.databinding.ListRamadanBinding
import com.example.masalafoodapplication.databinding.ListStepByStepRecipesBinding
import com.example.masalafoodapplication.databinding.ListVegetarianRecipesBinding
import com.example.masalafoodapplication.ui.base.BaseAdapter
import com.example.masalafoodapplication.ui.home.HomeInteractionListener
import com.example.masalafoodapplication.ui.home.HomeItem
import com.example.masalafoodapplication.util.Constants.UNKNOWN_HOME_ITEM_TYPE
import com.example.masalafoodapplication.util.loadImage

class HomeAdapter(
    private var items: List<HomeItem>,
    private val listener: HomeInteractionListener,
) : BaseAdapter<HomeItem, ViewBinding>(items) {

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewBinding {
        return when (viewType) {
            BANNER -> ItemBannerBinding.inflate(inflater, parent, false)
            QUICK_AND_EASY -> ListQuickRecipesBinding.inflate(inflater, parent, false)
            POPULAR_CUISINES -> ListCuisinesBinding.inflate(inflater, parent, false)
            RAMADAN_2023 -> ListRamadanBinding.inflate(inflater, parent, false)
            VEGETARIAN -> ListVegetarianRecipesBinding.inflate(inflater, parent, false)
            STEP_BY_STEP -> ListStepByStepRecipesBinding.inflate(inflater, parent, false)
            INDIAN_FOOD_HISTORY -> ItemHistoryBinding.inflate(inflater, parent, false)
            else -> throw IllegalArgumentException(UNKNOWN_HOME_ITEM_TYPE)

        }
    }

    override fun bind(binding: ViewBinding, item: HomeItem) {
        when (binding) {
            is ItemBannerBinding -> bindBanner(binding, item as HomeItem.Banner)
            is ListQuickRecipesBinding -> bindQuickRecipes(binding, item as HomeItem.QuickAndEasy)
            is ListCuisinesBinding -> bindPopularCuisines(binding, item as HomeItem.PopularCuisines)
            is ItemHistoryBinding -> bindIndianFoodHistory(binding, item as HomeItem.FoodHistory)
            is ListRamadanBinding -> bindRamadan2023(binding, item as HomeItem.Ramadan2023)
            is ListVegetarianRecipesBinding -> bindVegetarian(binding, item as HomeItem.Vegetarian)
            is ListStepByStepRecipesBinding -> bindStepByStep(binding, item as HomeItem.StepByStep)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is HomeItem.Banner -> BANNER
            is HomeItem.QuickAndEasy -> QUICK_AND_EASY
            is HomeItem.PopularCuisines -> POPULAR_CUISINES
            is HomeItem.Ramadan2023 -> RAMADAN_2023
            is HomeItem.Vegetarian -> VEGETARIAN
            is HomeItem.StepByStep -> STEP_BY_STEP
            is HomeItem.FoodHistory -> INDIAN_FOOD_HISTORY
        }
    }

    private fun bindBanner(binding: ItemBannerBinding, item: HomeItem.Banner) {
        binding.apply {
            imageBanner.loadImage(item.data)
            root.setOnClickListener { listener.onBannerClicked() }
        }
    }

    private fun bindQuickRecipes(binding: ListQuickRecipesBinding, item: HomeItem.QuickAndEasy) {
        binding.apply {
            recyclerRecipes.adapter = RecipesAdapter(item.data, listener)
            buttonSeeMore.setOnClickListener { listener.onSeeMoreClicked(item.type) }
        }
    }

    private fun bindRamadan2023(binding: ListRamadanBinding, item: HomeItem.Ramadan2023) {
        binding.apply {
            recyclerRecipes.adapter = RecipesAdapter(item.data, listener)
            buttonSeeMore.setOnClickListener { listener.onSeeMoreClicked(item.type) }
        }
    }

    private fun bindVegetarian(binding: ListVegetarianRecipesBinding, item: HomeItem.Vegetarian) {
        binding.apply {
            recyclerRecipes.adapter = RecipesAdapter(item.data, listener)
            buttonSeeMore.setOnClickListener { listener.onSeeMoreClicked(item.type) }
        }
    }

    private fun bindStepByStep(binding: ListStepByStepRecipesBinding, item: HomeItem.StepByStep) {
        binding.apply {
            recyclerRecipes.adapter = RecipesAdapter(item.data, listener)
            buttonSeeMore.setOnClickListener { listener.onSeeMoreClicked(item.type) }
        }
    }

    private fun bindPopularCuisines(binding: ListCuisinesBinding, item: HomeItem.PopularCuisines) {
        binding.recyclerCuisines.adapter = CuisinesAdapter(item.data, listener)
    }


    private fun bindIndianFoodHistory(binding: ItemHistoryBinding, item: HomeItem.FoodHistory) {
        binding.apply {
            imageFoodHistory.loadImage(item.data)
            root.setOnClickListener { listener.onIndianFoodHistoryClicked() }
        }
    }

    companion object {
        const val BANNER = 0
        const val RAMADAN_2023 = 1
        const val QUICK_AND_EASY = 2
        const val POPULAR_CUISINES = 3
        const val VEGETARIAN = 4
        const val STEP_BY_STEP = 5
        const val INDIAN_FOOD_HISTORY = 6
    }
}