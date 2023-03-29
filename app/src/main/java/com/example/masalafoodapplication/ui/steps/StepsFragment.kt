package com.example.masalafoodapplication.ui.steps


import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.data.domain.enums.FoodDetaisType
import com.example.masalafoodapplication.databinding.FragmentStepsBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.data.domain.models.FoodDetailsItem
import com.example.masalafoodapplication.ui.steps.steps.StepsAdapter





class StepsFragment : BaseFragment<FragmentStepsBinding>() {
    private lateinit var food: Food
    private lateinit var adapter: StepsAdapter
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentStepsBinding
        get() = FragmentStepsBinding::inflate

    override fun setup() {
        listenToFragmentResult()
    }

    private fun listenToFragmentResult() {
        parentFragmentManager.setFragmentResultListener(
            Constants.KEY_FOOD_ID,
            this
        ) { _, result ->
            food = dataManager.getFoodById(result.getInt(Constants.KEY_FOOD_ID))
            val items  =mutableListOf<FoodDetailsItem<Any>>()
            items.add(FoodDetailsItem("", FoodDetaisType.VIEW_TYPE_IMAGE))
            items.add(FoodDetailsItem("", FoodDetaisType.VIEW_TYPE_TEXT))
            items.add(FoodDetailsItem(food, FoodDetaisType.VIEW_TYPE_CHECKBOX))
            binding.recyclerCheckboxSteps.adapter = StepsAdapter(items, this)


        }
    }

    override fun onClicks() {
        binding.stepsToolbar.setNavigationOnClickListener {
            onBack(food.id, Constants.INGREDIENT)
        }
        binding.buttonFinish.setOnClickListener {
            parentFragmentManager.popBackStack(Constants.FOOD_DETAILS, 1)
        }
    }
}