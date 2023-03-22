package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.databinding.FragmentFoodDetailBinding
import com.example.masalafoodapplication.util.Constants
import com.google.android.material.chip.Chip
import com.kiko.fillapp.data.domain.Food


class FoodDetailFragment : BaseFragment<FragmentFoodDetailBinding>() {
    private lateinit var food: Food
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFoodDetailBinding
        get() = FragmentFoodDetailBinding::inflate

    override fun setup() {
        parentFragmentManager.setFragmentResultListener(
            Constants.FOOD_DETAILS, this
        ) { _, result ->
            food = result.getParcelable(Constants.FOOD_DETAILS)!!
            chooseChips(food)
        }

    }

    override fun onClicks() {
        binding.back.setOnClickListener {
            onBack()
        }
        binding.startButton.setOnClickListener {
            transitionToWithBackStack(IngredientFragment(), Constants.INGREDIENT)
            newInstance(food, Constants.INGREDIENT)
        }
    }


    private fun chooseChips(food: Food?) {
        binding.chiceGroupChips.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = group.findViewById(checkedId)
            chip?.let {
                binding.descriptionTv.text = food?.makeRecipe ?: "empty"
                if (it.text.toString() == Constants.INGREDIENT) {
                    binding.descriptionTv.text =
                        food?.ingredients?.split(";")?.joinToString(separator = "\n")
                            ?: "empty"
                } else if (it.text.toString() == Constants.STEPS) {
                    binding.descriptionTv.text =
                        food?.ingredients?.split(";")?.joinToString(separator = "\n")
                            ?: "empty"
                }
            }
        }
    }
}

