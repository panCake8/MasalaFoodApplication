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
import com.example.masalafoodapplication.util.loadImage
import com.kiko.fillapp.data.domain.Food


class ExploreFragment : BaseFragment<FragmentExploreBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentExploreBinding
        get() = FragmentExploreBinding::inflate

    override fun setup() {

    }

    override fun onClicks() {
        binding.searchBar.addTextChangedListener { text ->
            val list = DataManager.search(text.toString().lowercase())
            getData(text.toString(), list)
        }
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