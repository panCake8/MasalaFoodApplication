package com.example.masalafoodapplication.ui.ingredient

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.databinding.FragmentIngredientBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.ui.ingredient.adapter.IngredientAdapter
import com.example.masalafoodapplication.ui.steps.StepsFragment


class IngredientFragment : BaseFragment<FragmentIngredientBinding>() {
    private lateinit var food: Food
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentIngredientBinding
        get() = FragmentIngredientBinding::inflate

    override fun setup() {
        listenToFragmentResult()
    }

    private fun listenToFragmentResult() {
        parentFragmentManager.setFragmentResultListener(
            Constants.INGREDIENT,
            this
        ) { _, result ->
            food = DataManager.getFoodById(result.getInt(Constants.INGREDIENT))
            val adapter = IngredientAdapter(food)
            binding.checkboxRecycler.adapter = adapter
        }
    }

    override fun onClicks() {
        binding.ingredientToolbar.setNavigationOnClickListener {
            onBack(food.id, Constants.KEY_FOOD_ID)
        }
        binding.nextBtn.setOnClickListener {
            newInstance(food.id, Constants.KEY_FOOD_ID)
            transitionToWithBackStackAdd(
                StepsFragment(),
                this@IngredientFragment,
                Constants.INGREDIENT
            )

        }
    }

}