package com.example.masalafoodapplication.ui.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.FragmentExploreBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.explore.adapters.ExploreAdapter
import com.example.masalafoodapplication.ui.explore.adapters.ExploreListener
import com.example.masalafoodapplication.ui.filter.FilterFragment
import com.example.masalafoodapplication.ui.food_detail.FoodDetailFragment
import com.example.masalafoodapplication.util.Constants
import com.mindorks.editdrawabletext.DrawablePosition
import com.mindorks.editdrawabletext.onDrawableClickListener


class ExploreFragment : BaseFragment<FragmentExploreBinding>(), ExploreListener {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentExploreBinding
        get() = FragmentExploreBinding::inflate
    private lateinit var adapter: ExploreAdapter
    override fun setup() {
        adapter = ExploreAdapter(emptyList(), this)
        binding.recyclerSearchResult.adapter = adapter
        listenToFragmentResult()
        showAnimationSearch()
    }

    private fun listenToFragmentResult() {
        parentFragmentManager.setFragmentResultListener(
            Constants.FILTER,
            this
        ) { _, result ->
            val time = result.getFloat(Constants.TIME_MINUTES)
            val kitchens = result.getStringArrayList(Constants.KITCHENS)
            val ingredient = result.getStringArrayList(Constants.INGREDIENT)
            val filterList = dataManager.filterData(kitchens, ingredient, time)
            adapter = ExploreAdapter(filterList, this)
            binding.recyclerSearchResult.adapter = adapter
            hideAnimation()
        }
    }

    override fun onClicks() {
        binding.searchBar.addTextChangedListener {
            if (it.toString().isEmpty())
                showAnimationSearch()
            else {
                hideAnimation()
                search(it.toString())
            }
        }
        binding.searchBar.setDrawableClickListener(object : onDrawableClickListener {
            override fun onClick(target: DrawablePosition) {
                when (target) {
                    DrawablePosition.LEFT -> {}
                    DrawablePosition.RIGHT -> {
                        parentFragmentManager.popBackStack(Constants.EXPLORE, 1)
                        transitionToWithBackStackReplace(FilterFragment(), Constants.EXPLORE)
                    }
                }
            }

        })
    }

    private fun search(query: String) {
        val searchList = dataManager.search(query)
        if (searchList.isEmpty())
            showAnimationNotFound()
        else {
            hideAnimation()
            adapter.setData(searchList)
        }
    }

    private fun showLottieAndHideRecycle() {
        binding.recyclerSearchResult.visibility = View.GONE
        binding.viewLottieLayer.visibility = View.VISIBLE
    }

    private fun showAnimationSearch() {
        binding.apply {
            showLottieAndHideRecycle()
            viewLottieLayer.setAnimation(R.raw.search)
        }
    }

    private fun showAnimationNotFound() {
        binding.apply {
            showLottieAndHideRecycle()
            viewLottieLayer.setAnimation(R.raw.not_found)
        }
    }

    private fun hideAnimation() {
        binding.viewLottieLayer.visibility = View.GONE
        binding.recyclerSearchResult.visibility = View.VISIBLE
    }

    override fun onClickItem(food: Food) {
        newInstance(food.id, Constants.KEY_FOOD_ID)
        parentFragmentManager.popBackStack()
        transitionToWithBackStackReplace(FoodDetailFragment(), Constants.EXPLORE)
    }
}

