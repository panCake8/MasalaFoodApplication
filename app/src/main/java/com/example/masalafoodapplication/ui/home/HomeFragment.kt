package com.example.masalafoodapplication.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.data.domain.models.Cuisine
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.data.domain.models.HomeItem
import com.example.masalafoodapplication.data.domain.enums.HomeItemType
import com.example.masalafoodapplication.databinding.FragmentHomeBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.home.adapters.HomeAdapter
import com.example.masalafoodapplication.ui.home.adapters.HomeInteractionListener
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

    override fun onBannerClicked() {
        Toast.makeText(requireContext(), "Banner Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onRecipeClicked(food: Food) {
        Toast.makeText(requireContext(), "Recipe Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onCuisineClicked(cuisine: Cuisine) {
        Toast.makeText(requireContext(), "Cuisine Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onIndianFoodHistoryClicked() {
        Toast.makeText(requireContext(), "Indian Food History Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onSeeMoreClicked(type: HomeItemType) {
        when (type) {
            HomeItemType.QUICK_RECIPES -> Toast.makeText(
                requireContext(),
                "See More Quick Recipes Clicked",
                Toast.LENGTH_SHORT
            ).show()

            HomeItemType.CUISINES -> Toast.makeText(
                requireContext(),
                "See More Cuisines Clicked",
                Toast.LENGTH_SHORT
            ).show()

            HomeItemType.JUST_FOR_YOU -> Toast.makeText(
                requireContext(),
                "See More Just For You Clicked",
                Toast.LENGTH_SHORT
            ).show()

            HomeItemType.INDIAN_FOOD_HISTORY -> Toast.makeText(
                requireContext(),
                "See More Indian Food History Clicked",
                Toast.LENGTH_SHORT
            ).show()

            else -> {}
        }
    }

}

