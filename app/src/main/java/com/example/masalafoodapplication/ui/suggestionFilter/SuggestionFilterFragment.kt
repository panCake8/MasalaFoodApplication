package com.example.masalafoodapplication.ui.suggestionFilter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentSuggestionFilterBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.ingredient.adapter.IngredientChipAdapter
import com.example.masalafoodapplication.ui.ingredient.adapter.IngredientChipInteractionListener
import com.example.masalafoodapplication.ui.suggestion.SuggestionsFragment
import com.example.masalafoodapplication.util.Constants


class SuggestionFilterFragment : BaseFragment<FragmentSuggestionFilterBinding>(),
    IngredientChipInteractionListener {

    private var toSendData: String = ""
    private var collectedData = mutableListOf<String>()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSuggestionFilterBinding
        get() = FragmentSuggestionFilterBinding::inflate

    override fun setup() {
        val adapter =
            IngredientChipAdapter(DataManager.getIngredients(30) as MutableList<String>, this)
        binding.recyclerIngredient.adapter = adapter
    }

    override fun onClicks() {
        binding.buttonNext.setOnClickListener {

            for (chip in collectedData) {
                toSendData = "$toSendData$chip,"
            }
            Log.d("toSendData", toSendData)

            val bundle = Bundle()
            bundle.putString(Constants.SUGGESTION_FILTER, toSendData)
            val suggestionsFragment = SuggestionsFragment()
            suggestionsFragment.arguments = bundle


            if (toSendData.isEmpty()) {
                Toast.makeText(context, "select ingredients", Toast.LENGTH_SHORT).show()
            } else {
                toSendData = ""
                collectedData.clear()
                parentFragmentManager.popBackStack()
                transitionToWithBackStack(SuggestionsFragment())
            }
        }

    }

    override fun onChipClicks(chip: String, checked: Boolean) {
        if (checked)
            collectedData.add(chip)
        else
            collectedData.remove(chip)
    }

}