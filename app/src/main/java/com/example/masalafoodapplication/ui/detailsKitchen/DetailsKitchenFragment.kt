package com.example.masalafoodapplication.ui.detailsKitchen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.FragmentDetailsKitchenBinding
import com.example.masalafoodapplication.ui.detailsKitchen.adapter.DetailsItemType
import com.example.masalafoodapplication.ui.detailsKitchen.adapter.DetailsKitchenAdapter
import com.example.masalafoodapplication.ui.detailsKitchen.adapter.DetailsKitchenItem
import com.example.masalafoodapplication.ui.detailsKitchen.adapter.DetailsKitchenOnClick
import com.example.masalafoodapplication.ui.food_detail.FoodDetailFragment
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.util.Constants.KEY_CUISINE_NAME

class DetailsKitchenFragment : BaseFragment<FragmentDetailsKitchenBinding>(),
    DetailsKitchenOnClick {
    private lateinit var detailsKitchenItem: MutableList<DetailsKitchenItem<Any>>
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsKitchenBinding
        get() = FragmentDetailsKitchenBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        onClicks()
    }

    fun setup() {
        binding.apply {
            recyclerKitchen.adapter =
                DetailsKitchenAdapter(detailsKitchenItem, this@DetailsKitchenFragment)
        }
    }

    fun onClicks() {
        binding.toolbarKitchen.setNavigationOnClickListener {
            onBack()
        }
    }

    private fun onBack() {
        requireActivity().onBackPressed()
    }

    private fun bindData() {
        detailsKitchenItem = mutableListOf()
        parentFragmentManager.setFragmentResultListener(
            KEY_CUISINE_NAME, this
        ) { _, result ->

            val detailsKitchen = result.getString(KEY_CUISINE_NAME)

            detailsKitchenItem.add(
                DetailsKitchenItem(
                    dataManager.getRecipesByCuisine(detailsKitchen!!, 20),
                    DetailsItemType.POSTER
                )
            )
            detailsKitchenItem.add(
                DetailsKitchenItem(
                    dataManager.getRecipesByCuisine(detailsKitchen, 20),
                    DetailsItemType.POPULAR_DISHES
                )
            )
            binding.toolbarKitchen.title = detailsKitchen.toString()
        }

    }

    override fun onClickListener(food: Food) {
        newInstance(food.id, Constants.KEY_FOOD_ID)
        parentFragmentManager.popBackStack()
        transitionToWithBackStackReplace(FoodDetailFragment(), Constants.DETAILS_KITCHEN)
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