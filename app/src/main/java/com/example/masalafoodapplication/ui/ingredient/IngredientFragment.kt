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
    private lateinit var adapter: IngredientAdapter
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
            food = dataManager.getFoodById(result.getInt(Constants.INGREDIENT))
            adapter = IngredientAdapter(food)
            binding.checkboxRecycler.adapter = adapter
        }
    }

    override fun onClicks() {
        binding.ingredientToolbar.setNavigationOnClickListener {
            onBack()
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