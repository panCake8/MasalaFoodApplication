package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import com.bumptech.glide.Glide
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.databinding.FragmentFoodDetailBinding
import com.example.masalafoodapplication.util.Constants
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
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
            binding.dishName.text = food?.recipeName ?: "not found"
            binding.chiceGroupChips.check(R.id.description)
            binding.descriptionTv.text = food?.makeRecipe ?: "3"
            Glide.with(requireActivity()).load(food.imageUrl).into(binding.backgroundImage)
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
        binding.chiceGroupChips.setOnCheckedChangeListener(object : ChipGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: ChipGroup, @IdRes checkedId: Int) {
                val chip: Chip? = group?.findViewById(checkedId)
                chip?.let {
                    if (it.text.toString() == Cons.INGREDIENT) {
                        binding.descriptionTv.text =
                            food?.ingredients?.split(";")?.joinToString(separator = "\n")
                                ?: "Not Found"
                    } else if (it.text.toString() == Cons.STEPS) {
                        binding.descriptionTv.text =
                            food?.ingredients?.split(";")?.joinToString(separator = "\n")
                                ?: "Not Found"
                    } else {
                        binding.descriptionTv.text = food?.makeRecipe ?: "Not Found"
                    }
                }
            }
        })
    }

    object Cons {
        const val INGREDIENT = "Ingradient"
        const val STEPS = "Steps"

    }
}
