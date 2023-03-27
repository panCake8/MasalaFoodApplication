package com.example.masalafoodapplication.ui.steps


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.example.masalafoodapplication.databinding.FragmentStepsBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.ui.steps.adapters.StepsAdapter


class StepsFragment : BaseFragment<FragmentStepsBinding>() {
    private lateinit var food: Food

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentStepsBinding
        get() = FragmentStepsBinding::inflate

    override fun setup() {
        parentFragmentManager.setFragmentResultListener(
            Constants.STEPS,
            this
        ) { _, result ->
            food = result.getParcelable(Constants.STEPS)!!
            val adapter= StepsAdapter(food)
            binding.checkboxRecycler.adapter=adapter
        }
    }

    override fun onClicks() {
        binding.stepsToolbar.setOnClickListener {
            onBack()
        }
        binding.finishBtn.setOnClickListener {
            parentFragmentManager.popBackStack(Constants.FOOD_DETAILS, POP_BACK_STACK_INCLUSIVE)
        }
    }
}