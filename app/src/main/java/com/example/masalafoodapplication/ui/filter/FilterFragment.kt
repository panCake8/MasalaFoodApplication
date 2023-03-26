package com.example.masalafoodapplication.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.databinding.FragmentFilterBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.explore.ExploreFragment
import com.example.masalafoodapplication.util.Constants
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.slider.Slider

class FilterFragment : BaseFragment<FragmentFilterBinding>() {

    private lateinit var filterChipGroup1: ChipGroup
    private lateinit var filterChipGroup2: ChipGroup
    private lateinit var slider: Slider
    private lateinit var applyButton: Button
    private lateinit var resetButton: Button

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFilterBinding
        get() = FragmentFilterBinding::inflate

    override fun setup() {

    }

    override fun onClicks() {
        binding.filterToolbar.setNavigationOnClickListener {
            onBack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_filter, container, false)

        filterChipGroup1 = rootView.findViewById(R.id.filter_chip_group_1)
        filterChipGroup2 = rootView.findViewById(R.id.filter_chip_group_2)
        slider = rootView.findViewById(R.id.slider)
        applyButton = rootView.findViewById(R.id.apply_button)
        resetButton = rootView.findViewById(R.id.reset_button)

        applyButton.setOnClickListener {
            val selectedChips1 = getSelectedChips(filterChipGroup1)
            val selectedChips2 = getSelectedChips(filterChipGroup2)
            val sliderValue = slider.value
            parentFragmentManager.popBackStack()
            transitionToWithBackStack(ExploreFragment(), Constants.FILTER)
            newInstance(
                selectedChips1[0], selectedChips2[0], sliderValue.toString(),
                Constants.FILTER
            )
        }

        resetButton.setOnClickListener {
            resetChips(filterChipGroup1)
            resetChips(filterChipGroup2)
            slider.value = slider.valueFrom
        }

        return rootView
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
}