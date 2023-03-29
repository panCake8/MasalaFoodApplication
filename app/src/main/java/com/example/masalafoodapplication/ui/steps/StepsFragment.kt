package com.example.masalafoodapplication.ui.steps


import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentStepsBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.ui.food_detail.FoodDetailFragment
import com.example.masalafoodapplication.ui.steps.steps.StepsAdapter


class StepsFragment : BaseFragment<FragmentStepsBinding>() {
    private lateinit var food: Food

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentStepsBinding
        get() = FragmentStepsBinding::inflate

    override fun setup() {
        listenToFragmentResult()
    }

    private  fun listenToFragmentResult(){
        parentFragmentManager.setFragmentResultListener(
            Constants.KEY_FOOD_ID,
            this
        ){_,result->
            food=DataManager.getFoodById(result.getInt(Constants.KEY_FOOD_ID))
            val adapter=StepsAdapter(food)
            binding.recyclerCheckboxSteps.adapter=adapter

        }
    }

    override fun onClicks() {
        binding.toolbarSteps.setOnClickListener {
            onBack(food.id, Constants.KEY_FOOD_ID)
        }
        binding.buttonFinish.setOnClickListener {
            parentFragmentManager.popBackStack(Constants.FOOD_DETAILS, 1)
        }
    }
}