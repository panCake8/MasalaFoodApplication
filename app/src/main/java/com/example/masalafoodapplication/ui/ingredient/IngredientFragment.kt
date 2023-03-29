package com.example.masalafoodapplication.ui.ingredient

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.data.domain.enums.FoodDetaisType

import com.example.masalafoodapplication.databinding.FragmentIngredientBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.data.domain.models.FoodDetailsItem
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

            food = dataManager.getFoodById(result.getInt(Constants.INGREDIENT))
            val items  =mutableListOf<FoodDetailsItem<Any>>()
            items.add(FoodDetailsItem("",FoodDetaisType.VIEW_TYPE_IMAGE))
            items.add(FoodDetailsItem("",FoodDetaisType.VIEW_TYPE_TEXT))
            items.add(FoodDetailsItem(food,FoodDetaisType.VIEW_TYPE_CHECKBOX))
            binding.recyclerCheckboxIngredient.adapter = IngredientAdapter(items, this)

        }
    }


    override fun onClicks() {
        binding.toolbarIngredient.setNavigationOnClickListener {
            onBack(food.id, Constants.KEY_FOOD_ID)
        }
        binding.buttonNext.setOnClickListener {
            newInstance(food.id, Constants.KEY_FOOD_ID)
            transitionToWithBackStackAdd(
                StepsFragment(),
                this@IngredientFragment,
                Constants.INGREDIENT
            )

        }
    }

}