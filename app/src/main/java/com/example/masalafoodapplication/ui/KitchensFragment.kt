package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentKitchensBinding

private val listOfFood = DataManager.getAllFood()

class KitchensFragment : BaseFragment<FragmentKitchensBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentKitchensBinding
        get() = FragmentKitchensBinding::inflate

    override fun setup() {
        setImages()
        setInfoText()
    }

    private fun setImages() {
        val asianKitchenImage = getImageUrl(ASIAN)

        val arabianKitchenImage = getImageUrl(ARAB)

        val italianKitchenImage = getImageUrl(ITALIAN)

        val kashmirKitchenImage = getImageUrl(KASHMIRI)

        setGlideImage(asianKitchenImage, binding.asianKitchenImg)

        setGlideImage(arabianKitchenImage, binding.arabKitchenImg)

        setGlideImage(italianKitchenImage, binding.italianKitchenImg)

        setGlideImage(kashmirKitchenImage, binding.kashmirKitchenImg)
    }

    private fun getImageUrl(cuisine: String) =
        listOfFood.filter {
            it.Cuisine == cuisine
        }[0].imageUrl


    private fun setGlideImage(imgUrl: String, img: ImageView) =
        Glide.with(requireContext()).load(imgUrl).into(img)

    override fun onClicks() {

    }

    private fun setInfoText() {
        binding.asianKitchenInfo.text = DataManager.getAllFood().filter {
            it.Cuisine == ASIAN
        }[0].cleaned.replace(';', ',')

        binding.arabKitchenInfo.text = DataManager.getAllFood().filter {
            it.Cuisine == ARAB
        }[0].cleaned.replace(';', ',')
    }

    companion object {
        private const val ASIAN = "Asian"
        private const val ARAB = "Arab"
        private const val ITALIAN = "Italian Recipes"
        private const val KASHMIRI = "Kashmiri"
    }

}