package com.example.masalafoodapplication.ui.suggestionFilter


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.masalafoodapplication.databinding.FragmentSuggestionFilterBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.suggestionFilter.adapter.SuggestionFilterInteractionListener
import com.example.masalafoodapplication.ui.suggestion.SuggestionsFragment
import com.example.masalafoodapplication.ui.suggestionFilter.adapter.SuggestionFilterAdapter
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.util.Constants.TAG_SUGGESTIONS
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager


class SuggestionFilterFragment : BaseFragment<FragmentSuggestionFilterBinding>(),
    SuggestionFilterInteractionListener {

    private var selectedIngredient = ArrayList<String>()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSuggestionFilterBinding
        get() = FragmentSuggestionFilterBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        onClicks()
    }

    fun setup() {
        val adapter =
            SuggestionFilterAdapter(dataManager.getIngredients(30) as MutableList<String>, this)
        binding.recyclerIngredient.adapter = adapter
        binding.recyclerIngredient.layoutManager = FlexboxLayoutManager(context)
            .apply { flexDirection = FlexDirection.ROW }
    }

    fun onClicks() {
        binding.buttonNext.setOnClickListener {
            if (selectedIngredient.isEmpty()) {
                Toast.makeText(context, "select ingredients", Toast.LENGTH_SHORT).show()
            } else {
                newInstanceToSuggestion(selectedIngredient, Constants.SUGGESTION_FILTER)
                transitionToWithBackStackAdd(SuggestionsFragment(), this, TAG_SUGGESTIONS)
                selectedIngredient.clear()
            }
        }

    }

    override fun onChipClicks(chip: String, checked: Boolean) {
        if (checked)
            selectedIngredient.add(chip)
        else
            selectedIngredient.remove(chip)
    }

}