package com.example.masalafoodapplication.ui.steps


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.masalafoodapplication.data.domain.enums.FoodDetaisType
import com.example.masalafoodapplication.databinding.FragmentStepsBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.data.domain.models.FoodDetailsItem
import com.example.masalafoodapplication.ui.steps.adapter.StepsAdapter


class StepsFragment : BaseFragment<FragmentStepsBinding>() {
    private lateinit var food: Food
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentStepsBinding
        get() = FragmentStepsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        onClicks()
    }

    fun setup() {
        listenToFragmentResult()
    }

    private fun listenToFragmentResult() {
        parentFragmentManager.setFragmentResultListener(
            Constants.KEY_FOOD_ID,
            this
        ) { _, result ->
            food = dataManager.getFoodById(result.getInt(Constants.KEY_FOOD_ID))
            val items = mutableListOf<FoodDetailsItem<Any>>()
            items.add(FoodDetailsItem("", FoodDetaisType.VIEW_TYPE_IMAGE))
            items.add(FoodDetailsItem("", FoodDetaisType.VIEW_TYPE_TEXT))
            items.add(FoodDetailsItem(food, FoodDetaisType.VIEW_TYPE_CHECKBOX))
            binding.recyclerCheckboxSteps.adapter = StepsAdapter(items)
        }
    }

    fun onClicks() {
        binding.toolbarSteps.setNavigationOnClickListener {
            onBack(food.id, Constants.INGREDIENT)
        }
        binding.buttonFinish.setOnClickListener {
            parentFragmentManager.popBackStack(Constants.FOOD_DETAILS, 1)
        }
    }

    private fun onBack(id: Int, tag: String) {
        newInstance(id, tag)
        requireActivity().onBackPressed()
    }

    private fun newInstance(int: Int, key: String) {
        val bundle = Bundle()
        bundle.putInt(key, int)
        parentFragmentManager.setFragmentResult(key, bundle)
    }
}