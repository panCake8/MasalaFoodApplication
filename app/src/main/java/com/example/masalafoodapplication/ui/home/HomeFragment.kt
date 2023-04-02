package com.example.masalafoodapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.models.Cuisine
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.FragmentHomeBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.base.HomeActivity
import com.example.masalafoodapplication.ui.detailsKitchen.DetailsKitchenFragment
import com.example.masalafoodapplication.ui.food_detail.FoodDetailFragment
import com.example.masalafoodapplication.ui.history.HistoryFragment
import com.example.masalafoodapplication.ui.home.adapters.HomeAdapter
import com.example.masalafoodapplication.ui.see_more.SeeMoreFragment
import com.example.masalafoodapplication.util.Constants.ARAB
import com.example.masalafoodapplication.util.Constants.INDIAN
import com.example.masalafoodapplication.util.Constants.KEY_CUISINE_NAME
import com.example.masalafoodapplication.util.Constants.KEY_FOOD_ID
import com.example.masalafoodapplication.util.Constants.KEY_SEE_MORE
import com.example.masalafoodapplication.util.Constants.TAG_FOOD_DETAILS
import com.example.masalafoodapplication.util.Constants.TAG_HISTORY
import com.example.masalafoodapplication.util.Constants.TAG_KITCHEN_DETAILS
import com.example.masalafoodapplication.util.Constants.TAG_SEE_MORE


class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeInteractionListener {
    private lateinit var homeItems: MutableList<HomeItem>
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindHomeItems()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    fun setup() {
        (activity as HomeActivity).showBottomNavBar()
        binding.recyclerHome.adapter = HomeAdapter(homeItems, this)
    }

    private fun bindHomeItems() {
        homeItems = mutableListOf()
        homeItems.add(HomeItem.Banner(dataManager.getRandomFoodImage()))
        homeItems.add(HomeItem.Ramadan2023(dataManager.getRecipesByCuisine(ARAB, 20)))
        homeItems.add(HomeItem.QuickAndEasy(dataManager.getRandomQuickRecipes(20)))
        homeItems.add(HomeItem.PopularCuisines(dataManager.getMostRichCuisines(20)))
        homeItems.add(HomeItem.Vegetarian(dataManager.getVegetarianRecipes(20)))
        homeItems.add(HomeItem.StepByStep(dataManager.getMostStepsRecipes(5)))
        homeItems.add(HomeItem.FoodHistory(dataManager.getImageByCuisine(INDIAN)))
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
            HomeItemType.INDIAN_FOOD_HISTORY -> transitionToWithBackStackReplace(
                HistoryFragment(), TAG_HISTORY
            )

            HomeItemType.VEGETARIAN,
            HomeItemType.RAMADAN_2023,
            HomeItemType.STEP_BY_STEP,
            HomeItemType.QUICK_AND_EASY,
            -> {
                newInstance(type, KEY_SEE_MORE)
                transitionToWithBackStackReplace(SeeMoreFragment(), TAG_SEE_MORE)
            }

            else -> {}
        }
    }

    private fun transitionToWithBackStackReplace(fragment: Fragment, tag: String) {
        parentFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(tag)
            setReorderingAllowed(true)
        }
    }

    private fun newInstance(string: String, key: String) {
        val bundle = Bundle()
        bundle.putString(key, string)
        parentFragmentManager.setFragmentResult(key, bundle)
    }

    private fun newInstance(int: Int, key: String) {
        val bundle = Bundle()
        bundle.putInt(key, int)
        parentFragmentManager.setFragmentResult(key, bundle)
    }

    private fun newInstance(type: HomeItemType, key: String) {
        val bundle = Bundle()
        bundle.putSerializable(key, type)
        parentFragmentManager.setFragmentResult(key, bundle)
    }
}