package com.example.masalafoodapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.data.domain.enums.HomeItemType
import com.example.masalafoodapplication.data.domain.models.Cuisine
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.data.domain.models.HomeItem
import com.example.masalafoodapplication.databinding.FragmentHomeBinding
import com.example.masalafoodapplication.ui.detailsKitchen.DetailsKitchenFragment
import com.example.masalafoodapplication.ui.HistoryFragment
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.food_detail.FoodDetailFragment
import com.example.masalafoodapplication.ui.home.adapters.HomeAdapter
import com.example.masalafoodapplication.ui.home.adapters.HomeInteractionListener
import com.example.masalafoodapplication.ui.quick_recipes.QuickRecipesFragment
import com.example.masalafoodapplication.ui.random_recipes.RandomRecipesFragment
import com.example.masalafoodapplication.util.Constants.INDIAN
import com.example.masalafoodapplication.util.Constants.KEY_CUISINE_NAME
import com.example.masalafoodapplication.util.Constants.KEY_FOOD_ID
import com.example.masalafoodapplication.util.Constants.TAG_FOOD_DETAILS
import com.example.masalafoodapplication.util.Constants.TAG_HISTORY
import com.example.masalafoodapplication.util.Constants.TAG_JUST_FOR_YOU
import com.example.masalafoodapplication.util.Constants.TAG_KITCHEN_DETAILS
import com.example.masalafoodapplication.util.Constants.TAG_QUICK_RECIPES


class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeInteractionListener {
    private lateinit var homeItems: MutableList<HomeItem<Any>>
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindHomeItems()
    }

    override fun setup() {
        binding.recyclerHome.adapter = HomeAdapter(homeItems, this)
    }


    private fun bindHomeItems() {
        homeItems = mutableListOf()
        homeItems.add(HomeItem(DataManager.getRandomFoodImage(), HomeItemType.BANNER))
        homeItems.add(HomeItem(DataManager.getRandomQuickRecipes(20), HomeItemType.QUICK_RECIPES))
        homeItems.add(HomeItem(DataManager.getCuisines(20), HomeItemType.CUISINES))
        homeItems.add(HomeItem(DataManager.getRandomFoods(20), HomeItemType.JUST_FOR_YOU))
        homeItems.add(
            HomeItem(DataManager.getImageByCuisine(INDIAN), HomeItemType.INDIAN_FOOD_HISTORY)
        )


    }

    override fun onBannerClicked() {}

    override fun onRecipeClicked(food: Food) {
        newInstance(food.id, KEY_FOOD_ID)
        transitionToWithBackStackReplace(FoodDetailFragment(), TAG_FOOD_DETAILS)
    }

    override fun onCuisineClicked(cuisine: Cuisine) {
        newInstance(cuisine.name, KEY_CUISINE_NAME)
        transitionToWithBackStackReplace(DetailsKitchenFragment(), TAG_KITCHEN_DETAILS)
    }

    override fun onIndianFoodHistoryClicked() =
        transitionToWithBackStackReplace(HistoryFragment(), TAG_HISTORY)

    override fun onSeeMoreClicked(type: HomeItemType) {
        when (type) {
            HomeItemType.QUICK_RECIPES -> transitionToWithBackStackReplace(
                QuickRecipesFragment(), TAG_QUICK_RECIPES
            )

            HomeItemType.JUST_FOR_YOU -> transitionToWithBackStackReplace(
                RandomRecipesFragment(), TAG_JUST_FOR_YOU
            )

            HomeItemType.INDIAN_FOOD_HISTORY -> transitionToWithBackStackReplace(
                HistoryFragment(), TAG_HISTORY
            )

            else -> {}
        }
    }


}

