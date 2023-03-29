package com.example.masalafoodapplication.ui.kitchen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.databinding.FragmentDetailsKitchenBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.util.loadImage
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.ui.food_detail.FoodDetailFragment
import com.example.masalafoodapplication.ui.kitchen.adapter.DetailsKitchenAdapter
import com.example.masalafoodapplication.ui.kitchen.adapter.DetailsKitchenOnClick
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.util.Constants.KEY_CUISINE_NAME

class DetailsKitchenFragment : BaseFragment<FragmentDetailsKitchenBinding>(),
    DetailsKitchenOnClick {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsKitchenBinding
        get() = FragmentDetailsKitchenBinding::inflate

    override fun setup() {
        listenToFragmentResult()
    }

    override fun onClicks() {
        binding.kitchenToolbar.setNavigationOnClickListener {
            onBack()
        }
    }

    private fun listenToFragmentResult() {
        parentFragmentManager.setFragmentResultListener(
            KEY_CUISINE_NAME,
            this
        ) { _, result ->
            val cuisineName = result.getString(KEY_CUISINE_NAME)
            cuisineName?.let {
                bindData(dataManager.getRecipesByCuisine(it))
            }
        }
    }

    private fun bindData(recipes: List<Food>) {
        binding.apply {
            kitchenToolbar.title = recipes.first().cuisine
            detailsImgPoster.loadImage(dataManager.getImageByCuisine(recipes.first().cuisine))
            detailsRecyclerView.adapter =
                DetailsKitchenAdapter(recipes, this@DetailsKitchenFragment)
        }
    }

    override fun onClickListener(food: Food) {
        newInstance(food.id, Constants.KEY_FOOD_ID)
        parentFragmentManager.popBackStack()
        transitionToWithBackStackReplace(FoodDetailFragment(), Constants.DETAILS_KITCHEN)
    }
}