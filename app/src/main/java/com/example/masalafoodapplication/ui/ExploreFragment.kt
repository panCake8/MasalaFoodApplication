package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentExploreBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.util.loadImage
import com.example.masalafoodapplication.data.domain.Food
import com.mindorks.editdrawabletext.DrawablePosition
import com.mindorks.editdrawabletext.onDrawableClickListener


class ExploreFragment : BaseFragment<FragmentExploreBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentExploreBinding
        get() = FragmentExploreBinding::inflate

    override fun setup() {
        parentFragmentManager.setFragmentResultListener(
            Constants.FILTER,
            this
        ) { _, result ->
            val list = DataManager.getAllFood().filter {
                it.cuisine != result.getString("foodName")
                        || it.cleaned != result.getString("cleaned")
                        || it.timeMinutes != result.getString("value").toString().toInt()
            }
            showDataHideLottie()
            bindSearchResult(list)


        }

    }


    override fun onClicks() {
        binding.searchBar.addTextChangedListener { text ->
            val list = DataManager.search(text.toString().lowercase())
            getData(text.toString(), list)
        }
        binding.searchBar.setDrawableClickListener(object : onDrawableClickListener {
            override fun onClick(target: DrawablePosition) {
                when (target) {
                    DrawablePosition.RIGHT -> {
                        transitionToWithBackStack(FilterFragment(), Constants.EXPLORE)
                    }
                    else -> {}
                }
            }
        })
    }


    private fun getData(value: String?, list: List<Food>) {
        if (value?.isEmpty() == true) {
            hideDataShowLottie()
            lottieTryToSearch()
        } else {
            if (list.isEmpty()) {
                hideDataShowLottie()
                lottieNotFoundSearch()
                return
            }
            try {
                showDataHideLottie()
                bindSearchResult(list)
            } catch (e: Exception) {
            }
        }
    }

    private fun bindSearchResult(list: List<Food>) {
        binding.apply {
            listOf(
                imvFood1, imvFood2,
                imvFood3, imvFood4,
                imvFood5, imvFood6,
            ).forEachIndexed { index, imv ->
                setImages(list[index].imageUrl, imv)
            }
            listOf(
                txvFoodName1, txvFoodName2,
                txvFoodName3, txvFoodName4,
                txvFoodName5, txvFoodName6,
            ).forEachIndexed { index, tv ->
                setTitles(list[index].cuisine, tv)
            }
            listOf(
                descriptionTv1, descriptionTv2,
                descriptionTv3, descriptionTv4,
                descriptionTv5, descriptionTv6,
            ).forEachIndexed { index, dv ->
                setTitles(list[index].makeRecipe, dv)
            }
        }
    }

    private fun showDataHideLottie() {
        binding.cardContainers.visibility = View.VISIBLE
        binding.lottieLayer.visibility = View.GONE
    }

    private fun lottieNotFoundSearch() {
        binding.lottieLayer.setAnimation(R.raw.not_found)
    }

    private fun lottieTryToSearch() {
        binding.lottieLayer.setAnimation(R.raw.search)
    }

    private fun hideDataShowLottie() {
        binding.cardContainers.visibility = View.GONE
        binding.lottieLayer.visibility = View.VISIBLE
    }

    private fun setImages(url: String, imageView: ImageView) {
        imageView.loadImage(url)
    }

    private fun setTitles(title: String, textView: TextView) {
        textView.text = title
    }
}