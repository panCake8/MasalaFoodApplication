package com.example.masalafoodapplication.ui.suggestion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.FragmentSuggestionsBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.food_detail.FoodDetailFragment
import com.example.masalafoodapplication.ui.suggestion.adapters.SuggestionOnClick
import com.example.masalafoodapplication.ui.suggestion.adapters.SuggestionsAdapter
import com.example.masalafoodapplication.util.Constants

class SuggestionsFragment : BaseFragment<FragmentSuggestionsBinding>(), SuggestionOnClick {
    private var data: List<String>? = null
    private lateinit var adapter: SuggestionsAdapter
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSuggestionsBinding
        get() = FragmentSuggestionsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        onClicks()
    }

    fun setup() {
        listenToFragmentResult()
    }

    fun onClicks() {
        binding.toolbarSuggestion.setNavigationOnClickListener {
            onBack()
        }
    }

    private fun bind(data: List<String>): List<SuggestionsItems> {
        val list = mutableListOf<SuggestionsItems>()
        list.add(
            SuggestionsItems(
                BREAKFAST, dataManager.splitFoodsIntoThreeMeals( BREAKFAST, data)
            )
        )

        list.add(
            SuggestionsItems(
                 LUNCH, dataManager.splitFoodsIntoThreeMeals( LUNCH, data)
            )
        )

        list.add(
            SuggestionsItems(
                 DINNER, dataManager.splitFoodsIntoThreeMeals( DINNER, data)
            )
        )

        return list
    }

    override fun onClickListener(food: Food) {
        newInstance(food.id, Constants.KEY_FOOD_ID)
        parentFragmentManager.popBackStack()
        transitionToWithBackStackReplace(FoodDetailFragment(), Constants.SUGGESTIONS)
    }

    private fun listenToFragmentResult() {
        parentFragmentManager.setFragmentResultListener(
            Constants.SUGGESTION_FILTER,
            this
        ) { _, result ->
            data = result.getStringArrayList(Constants.SUGGESTION_FILTER)
            adapter = data?.let { bind(it) }?.let { SuggestionsAdapter(it, this) }!!
            binding.recyclerSuggestion.adapter = adapter
        }
    }

    companion object{
        const val BREAKFAST = "Breakfast"
        const val LUNCH = "Lunch"
        const val DINNER = "Dinner"
    }

}