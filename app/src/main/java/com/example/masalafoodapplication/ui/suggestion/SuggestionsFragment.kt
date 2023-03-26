package com.example.masalafoodapplication.ui.suggestion

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentSuggestionsBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.suggestion.adapters.SuggestionsAdapter
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.util.SuggestionOnClick
import com.example.masalafoodapplication.util.SuggestionsItems

class SuggestionsFragment : BaseFragment<FragmentSuggestionsBinding>(),SuggestionOnClick {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSuggestionsBinding
        get() = FragmentSuggestionsBinding::inflate

    override fun setup() {
        //fakeData
        val data = listOf("salt", "amchur (dry mango powder)", "karela (bitter gourd pavakkai)")

        val adapter= SuggestionsAdapter(bind(data),this)
        binding.sugRv.adapter=adapter

    }

    override fun onClicks() {
        binding.sugTbTopAppBar.setOnClickListener {
            onBack()
        }
    }
    private fun bind(data:List<String>):List<SuggestionsItems>{
        val list= mutableListOf<SuggestionsItems>()
        list.add(SuggestionsItems(
            Constants.BREAKFAST
            ,DataManager.splitFoodsIntoThreeMeals(Constants.BREAKFAST,data)))

        list.add(SuggestionsItems(
            Constants.LUNCH
            ,DataManager.splitFoodsIntoThreeMeals(Constants.LUNCH,data)))

        list.add(SuggestionsItems(
            Constants.DINNER
            ,DataManager.splitFoodsIntoThreeMeals(Constants.DINNER,data)))

        return list
    }

    override fun onClickListener(nameFood: String) {
        Toast.makeText(
            requireContext().applicationContext
            ,nameFood
            ,Toast.LENGTH_LONG).show()
    }

}