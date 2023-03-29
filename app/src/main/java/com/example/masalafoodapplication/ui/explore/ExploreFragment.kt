package com.example.masalafoodapplication.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.airbnb.lottie.LottieDrawable
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        onClicks()
    }

    fun setup() {
        adapter = ExploreAdapter(emptyList(), this)
        binding.recyclerSearchResult.adapter = adapter
        listenToFragmentResult()
        setupAnimation()
    }

    private fun listenToFragmentResult() {
        parentFragmentManager.setFragmentResultListener(
            Constants.FILTER,
            this
        ) { _, result ->
            val time = result.getFloat(Constants.TIME_MINUTES)
            val kitchens = result.getStringArrayList(Constants.KITCHENS)
            val ingredient = result.getStringArrayList(Constants.INGREDIENT)
            val foodsFilter = dataManager.filterData(kitchens, ingredient, time)
            if (foodsFilter.isNotEmpty()) {
                adapter = ExploreAdapter(foodsFilter, this)
                binding.recyclerSearchResult.adapter = adapter
                hideAnimation()
            } else
                showAnimation(AnimationType.NOT_FOUND)
        }
    }

    fun onClicks() {
        binding.searchBar.addTextChangedListener {
            if (it.toString().isEmpty())
                showAnimation(AnimationType.SEARCH)
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
            showAnimation(AnimationType.NOT_FOUND)
        else {
            hideAnimation()
            adapter.setData(searchList)
        }
    }

    private fun setupAnimation() {
        binding.apply {
            viewLottieLayer.setAnimation(R.raw.search)
            viewLottieLayer.repeatCount = LottieDrawable.INFINITE
            viewLottieLayer.playAnimation()
        }
    }

    private fun showAnimation(animationType: AnimationType) {
        binding.apply {
            binding.recyclerSearchResult.visibility = View.INVISIBLE
            binding.viewLottieLayer.visibility = View.VISIBLE
            when (animationType) {
                AnimationType.SEARCH -> viewLottieLayer.setAnimation(R.raw.search)
                AnimationType.NOT_FOUND -> viewLottieLayer.setAnimation(R.raw.not_found)
            }
            viewLottieLayer.repeatCount = LottieDrawable.INFINITE
            viewLottieLayer.playAnimation()
        }
    }

    private fun hideAnimation() {
        binding.viewLottieLayer.visibility = View.INVISIBLE
        binding.recyclerSearchResult.visibility = View.VISIBLE
    }

    override fun onClickItem(food: Food) {
        newInstance(food.id, Constants.KEY_FOOD_ID)
        transitionToWithBackStackReplace(FoodDetailFragment(), Constants.EXPLORE)
    }
}

