package com.example.masalafoodapplication.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.data.domain.enums.HomeItemType
import com.example.masalafoodapplication.data.domain.models.Cuisine
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.data.domain.models.HomeItem
import com.example.masalafoodapplication.databinding.FragmentHomeBinding
import com.example.masalafoodapplication.ui.DetailsKitchenFragment
import com.example.masalafoodapplication.ui.HistoryFragment
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.food_detail.FoodDetailFragment
import com.example.masalafoodapplication.ui.home.adapters.HomeAdapter
import com.example.masalafoodapplication.ui.home.adapters.HomeInteractionListener
import com.example.masalafoodapplication.ui.quick_recipes.QuickRecipesFragment
import com.example.masalafoodapplication.ui.random_recipes.RandomRecipesFragment
import com.example.masalafoodapplication.util.Constants.INDIAN


class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeInteractionListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun setup() {
        bindHomeItems()
    }


    private fun bindHomeItems() {
        val homeItems = mutableListOf<HomeItem<Any>>()
        homeItems.add(HomeItem(DataManager.getRandomFoodImage(), HomeItemType.BANNER))
        homeItems.add(HomeItem(DataManager.getRandomQuickRecipes(20), HomeItemType.QUICK_RECIPES))
        homeItems.add(HomeItem(DataManager.getCuisines(20), HomeItemType.CUISINES))
        homeItems.add(HomeItem(DataManager.getRandomFoods(20), HomeItemType.JUST_FOR_YOU))
        homeItems.add(
            HomeItem(DataManager.getImageByCuisine(INDIAN), HomeItemType.INDIAN_FOOD_HISTORY)
        )

        binding.recyclerHome.adapter = HomeAdapter(homeItems, this)
    }

    override fun onBannerClicked() {}

    override fun onRecipeClicked(food: Food) =
        transitionToWithBackStack(FoodDetailFragment(food.id))

    override fun onCuisineClicked(cuisine: Cuisine) =
        transitionToWithBackStack(DetailsKitchenFragment(cuisine.name))

    override fun onIndianFoodHistoryClicked() = transitionToWithBackStack(HistoryFragment())

    override fun onSeeMoreClicked(type: HomeItemType) {
        when (type) {
            HomeItemType.QUICK_RECIPES -> transitionToWithBackStack(QuickRecipesFragment())

            HomeItemType.CUISINES -> transitionToWithBackStack(QuickRecipesFragment())

            HomeItemType.JUST_FOR_YOU -> transitionToWithBackStack(RandomRecipesFragment())

            HomeItemType.INDIAN_FOOD_HISTORY -> transitionToWithBackStack(HistoryFragment())

            else -> {}
        }
    }

}

