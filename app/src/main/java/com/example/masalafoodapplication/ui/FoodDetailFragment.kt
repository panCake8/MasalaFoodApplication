package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.databinding.FragmentFoodDetailBinding
import com.example.masalafoodapplication.util.Constants
import com.google.android.material.chip.Chip
import com.kiko.fillapp.data.domain.Food


class FoodDetailFragment : BaseFragment<FragmentFoodDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFoodDetailBinding
        get() = FragmentFoodDetailBinding::inflate

    override fun setup() {
        val food = arguments?.getParcelable<Food>(Constants.KEY)
        chooseChips(food)
    }

    override fun onClicks() {
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

