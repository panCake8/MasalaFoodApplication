package com.example.masalafoodapplication.ui.suggestionFilter

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
import com.example.masalafoodapplication.util.Constants.TAG_SUGGESTIONS


class SuggestionFilterFragment : BaseFragment<FragmentSuggestionFilterBinding>(),
    IngredientChipInteractionListener {

    private var collectedData = ArrayList<String>()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSuggestionFilterBinding
        get() = FragmentSuggestionFilterBinding::inflate

    override fun setup() {
        val adapter =
            IngredientChipAdapter(DataManager.getIngredients(30) as MutableList<String>, this)
        binding.recyclerIngredient.adapter = adapter
    }

    override fun onClicks() {
        binding.buttonNext.setOnClickListener {
            if (collectedData.isEmpty()) {
                Toast.makeText(context, "select ingredients", Toast.LENGTH_SHORT).show()
            } else {
                newInstanceToSuggestion(collectedData, Constants.SUGGESTION_FILTER)
                transitionToWithBackStackReplace(SuggestionsFragment(), TAG_SUGGESTIONS)
                collectedData.clear()
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