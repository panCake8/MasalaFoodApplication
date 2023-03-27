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


class ExploreFragment : BaseFragment<FragmentExploreBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentExploreBinding
        get() = FragmentExploreBinding::inflate
    private lateinit var adapter: ExploreAdapter
    override fun setup() {
        adapter = ExploreAdapter(emptyList())
        binding.recyclerSearchResult.adapter = adapter
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

