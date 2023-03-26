package com.example.masalafoodapplication.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.databinding.FragmentSuggestionFilterBinding
import com.example.masalafoodapplication.ui.suggestion.SuggestionsFragment
import com.example.masalafoodapplication.util.Constants
import com.google.android.material.chip.Chip


class SuggestionFilterFragment : BaseFragment<FragmentSuggestionFilterBinding>() {

    private var toSendData = mutableListOf<String>()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSuggestionFilterBinding
        get() = FragmentSuggestionFilterBinding::inflate

    override fun setup() {

    }

    override fun onClicks() {
        binding.buttonNext.setOnClickListener {
            val chipGroupOne = binding.chipGroup.checkedChipIds
            for (id in chipGroupOne) {
                toSendData.add(view?.findViewById<Chip>(id)?.text.toString())
                Log.d("aaaaa", view?.findViewById<Chip>(id)?.text.toString())
            }
            val chipGroupTwo = binding.chipGroup2.checkedChipIds
            for (id in chipGroupTwo) {
                toSendData.add(view?.findViewById<Chip>(id)?.text.toString())
                Log.d("aaaaa", view?.findViewById<Chip>(id)?.text.toString())
            }
            val chipGroupThree = binding.chipGroup3.checkedChipIds
            for (id in chipGroupThree) {
                toSendData.add(view?.findViewById<Chip>(id)?.text.toString())
                Log.d("aaaaa", view?.findViewById<Chip>(id)?.text.toString())
            }
        }

//        binding.navbar.setNavigationOnClickListener {
//            onBack()
//        }
        binding.buttonNext.setOnClickListener {
            parentFragmentManager.popBackStack()
            transitionToWithBackStack(SuggestionsFragment(), Constants.SUGGESTIONS)
        }
    }


}