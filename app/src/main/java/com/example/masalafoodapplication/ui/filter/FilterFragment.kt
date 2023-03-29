package com.example.masalafoodapplication.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.databinding.FragmentFilterBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.explore.ExploreFragment
import com.example.masalafoodapplication.util.Constants
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class FilterFragment : BaseFragment<FragmentFilterBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFilterBinding
        get() = FragmentFilterBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        onClicks()
    }

    fun setup() {

    }

    fun onClicks() {
        binding.filterToolbar.setNavigationOnClickListener {
            onBack()
        }
        binding.buttonApply.setOnClickListener {
            val selectedChips1: List<String> = getSelectedChips(binding.filterChipKitchens)
            val selectedChips2: List<String> = getSelectedChips(binding.filterChipComponent)
            val sliderValue = binding.slider.value
            newInstanceToExplore(
                selectedChips1 as ArrayList<String>,
                selectedChips2 as ArrayList<String>,
                sliderValue,
                Constants.FILTER
            )
            parentFragmentManager.popBackStack(Constants.EXPLORE, 0)
            transitionToWithBackStackReplace(ExploreFragment(), Constants.FILTER)
        }
        binding.buttonReset.setOnClickListener {
            resetChips(binding.filterChipKitchens)
            resetChips(binding.filterChipComponent)
            binding.slider.value = binding.slider.valueFrom
        }
    }

    private fun getSelectedChips(chipGroup: ChipGroup): List<String> {
        val selectedChips: MutableList<String> = mutableListOf()
        for (i in 0 until chipGroup.childCount) {
            val chip = chipGroup.getChildAt(i) as Chip
            if (chip.isChecked) {
                selectedChips.add(chip.text.toString())
            }
        }

        return selectedChips
    }

    private fun resetChips(chipGroup: ChipGroup) {
        for (i in 0 until chipGroup.childCount) {
            val chip = chipGroup.getChildAt(i) as Chip
            chip.isChecked = false
        }
    }

    private fun onBack() {
        requireActivity().onBackPressed()
    }

    private fun newInstanceToExplore(
        listKitchens: ArrayList<String>,
        listIngredient: ArrayList<String>,
        time: Float,
        key: String
    ) {
        val bundle = Bundle().apply {
            putStringArrayList(Constants.KITCHENS, listKitchens)
            putStringArrayList(Constants.INGREDIENT, listIngredient)
            putFloat(Constants.TIME_MINUTES, time)
        }
        parentFragmentManager.setFragmentResult(key, bundle)
    }

    private fun transitionToWithBackStackReplace(fragment: Fragment, tag: String) {
        parentFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(tag)
            setReorderingAllowed(true)
        }
    }
}