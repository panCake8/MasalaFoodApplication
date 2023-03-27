package com.example.masalafoodapplication.ui.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentExploreBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.explore.adapters.ExploreAdapter
import com.example.masalafoodapplication.ui.filter.FilterFragment
import com.example.masalafoodapplication.util.Constants
import com.mindorks.editdrawabletext.DrawablePosition
import com.mindorks.editdrawabletext.onDrawableClickListener


class ExploreFragment : BaseFragment<FragmentExploreBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentExploreBinding
        get() = FragmentExploreBinding::inflate
    private lateinit var adapter: ExploreAdapter
    override fun setup() {
        adapter = ExploreAdapter(emptyList())
        binding.recyclerSearchResult.adapter = adapter
        listenToFragmentResult()
    }

    private fun listenToFragmentResult() {
        parentFragmentManager.setFragmentResultListener(
            Constants.FILTER,
            this
        ) { _, result ->
            val time = result.getFloat(Constants.FILTER)
            val kitchens = result.getStringArrayList(Constants.KEY_CUISINE_NAME)
            val ingredient = result.getStringArrayList(Constants.INGREDIENT)
            val filterList = DataManager.filterData(kitchens, ingredient, time)
            adapter = ExploreAdapter(filterList)
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
        val searchList = DataManager.search(query)
        if (searchList.isEmpty())
            showAnimationNotFound()
        else {
            hideAnimation()
            adapter.setData(searchList)
        }
    }

    private fun showLottieAndHideRecycle() {
        binding.recyclerSearchResult.visibility = View.GONE
        binding.lottieLayer.visibility = View.VISIBLE
    }

    private fun showAnimationSearch() {
        binding.apply {
            showLottieAndHideRecycle()
            lottieLayer.setAnimation(R.raw.search)
        }
    }

    private fun showAnimationNotFound() {
        binding.apply {
            showLottieAndHideRecycle()
            lottieLayer.setAnimation(R.raw.not_found)
        }
    }

    private fun hideAnimation() {
        binding.lottieLayer.visibility = View.GONE
        binding.recyclerSearchResult.visibility = View.VISIBLE
    }
}

