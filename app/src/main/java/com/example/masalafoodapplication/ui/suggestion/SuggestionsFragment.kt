package com.example.masalafoodapplication.ui.suggestion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.masalafoodapplication.R
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
                getString(R.string.breakfast), dataManager.splitFoodsIntoThreeMeals(BREAKFAST, data)
            )
        )

        list.add(
            SuggestionsItems(
                getString(R.string.lunch), dataManager.splitFoodsIntoThreeMeals(LUNCH, data)
            )
        )

        list.add(
            SuggestionsItems(
                getString(R.string.dinner), dataManager.splitFoodsIntoThreeMeals(DINNER, data)
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
            val ingredientAsString = result.getString(Constants.SUGGESTION_FILTER)
            if (ingredientAsString != null) {
                data = ingredientAsString.subSequence(1,ingredientAsString.length-1).split(", ")
            }
            adapter = data?.let { bind(it) }?.let { SuggestionsAdapter(it, this) }!!
            binding.recyclerSuggestion.adapter = adapter
        }
    }

    companion object {
        const val BREAKFAST = "Breakfast"
        const val LUNCH = "Lunch"
        const val DINNER = "Dinner"
    }

    private fun onBack() {
        requireActivity().onBackPressed()
    }

    private fun transitionToWithBackStackReplace(fragment: Fragment, tag: String) {
        parentFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(tag)
            setReorderingAllowed(true)
        }
    }

    private fun newInstance(int: Int, key: String) {
        val bundle = Bundle()
        bundle.putInt(key, int)
        parentFragmentManager.setFragmentResult(key, bundle)
    }
}