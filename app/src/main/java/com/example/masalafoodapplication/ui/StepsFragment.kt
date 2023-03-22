package com.example.masalafoodapplication.ui


import android.content.res.ColorStateList
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.commit
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentStepsBinding
import com.google.android.material.checkbox.MaterialCheckBox
import com.kiko.fillapp.data.domain.Food


class StepsFragment : BaseFragment<FragmentStepsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentStepsBinding
        get() = FragmentStepsBinding::inflate

    override fun setup() {
        getSteps(DataManager.getAllFood()[0])
    }

    override fun onClicks() {
        binding.topAppBar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.finishBtn.setOnClickListener {
            parentFragmentManager.commit {
                remove(StepsFragment())
            }
        }
    }

    private fun getSteps(foods: Food) {
        val options = foods.makeRecipe.split(";").toTypedArray()
        val linearLayoutOptions = binding.checkboxLayout
        for (option in options) {
            val checkBox = MaterialCheckBox(context)
            checkBox.id = View.generateViewId()
            checkBox.width = ViewGroup.LayoutParams.MATCH_PARENT
            checkBox.height = ViewGroup.LayoutParams.WRAP_CONTENT
            checkBox.text = option
            checkBox.layoutDirection = View.LAYOUT_DIRECTION_RTL
            checkBox.buttonTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.base_color
                )
            )
            checkBox.isChecked = false
            checkBox.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.text_medium)
            )
            checkBox.typeface = ResourcesCompat.getFont(binding.root.context, R.font.work_sans)
            linearLayoutOptions.addView(checkBox)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {

                } else {

                }
            }
        }
    }
}