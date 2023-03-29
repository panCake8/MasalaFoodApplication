package com.example.masalafoodapplication.ui.steps


import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.databinding.FragmentStepsBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.ui.steps.adapters.StepsAdapter


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
            adapter = StepsAdapter(food)
            binding.checkboxRecycler.adapter = adapter
        }
    }

    override fun onClicks() {
        binding.stepsToolbar.setNavigationOnClickListener {
            onBack(food.id, Constants.INGREDIENT)
        }
        binding.finishBtn.setOnClickListener {
            parentFragmentManager.popBackStack(Constants.FOOD_DETAILS, 1)
        }
    }
}