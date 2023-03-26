package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.masalafoodapplication.databinding.FragmentDetailsKitchenBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.util.loadImage
import com.example.masalafoodapplication.data.domain.models.Food


class DetailsKitchenFragment(val name: String) : BaseFragment<FragmentDetailsKitchenBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsKitchenBinding
        get() = FragmentDetailsKitchenBinding::inflate
    private lateinit var food: ArrayList<Food>
    override fun setup() {
        parentFragmentManager.setFragmentResultListener(
            name,
            this
        ) { _, result ->
            when (name) {
//                Constants.ASIAN -> {
//                    food = result.getParcelableArrayList(Constants.ASIAN)!!
//                    binding.apply {
//                        bindViews(
//                            imgOne, titleOne, infoOne, food[0].imageUrl,
//                            food[0].cuisine, food[0].timeMinutes
//                        )
//                        bindViews(
//                            imgTwo, titleTwo, infoTwo, food[1].imageUrl,
//                            food[1].cuisine, food[1].timeMinutes
//                        )
//                        bindViews(
//                            imgThree, titleThree, infoThree, food[2].imageUrl,
//                            food[2].cuisine, food[2].timeMinutes
//                        )
//                        bindViews(
//                            imgFour, titleFour, infoFour, food[3].imageUrl,
//                            food[3].cuisine, food[3].timeMinutes
//                        )
//                    }
//                }
//                Constants.ARAB -> {
//                    food = result.getParcelableArrayList(Constants.ARAB)!!
//                    binding.apply {
//                        bindViews(
//                            imgOne, titleOne, infoOne, food?.get(0)?.imageUrl,
//                            food?.get(0)?.cuisine, food?.get(0)?.timeMinutes
//                        )
//                        bindViews(
//                            imgTwo, titleTwo, infoTwo, food?.get(1)?.imageUrl,
//                            food?.get(1)?.cuisine, food?.get(1)?.timeMinutes
//                        )
//                        bindViews(
//                            imgThree, titleThree, infoThree, food?.get(2)?.imageUrl,
//                            food?.get(2)?.cuisine, food?.get(2)?.timeMinutes
//                        )
//                        bindViews(
//                            imgFour, titleFour, infoFour, food?.get(3)?.imageUrl,
//                            food?.get(3)?.cuisine, food?.get(3)?.timeMinutes
//                        )
//                    }
//                }
//
//                Constants.INDIAN -> {
//                    food = result.getParcelableArrayList(Constants.INDIAN)!!
//                    binding.apply {
//                        bindViews(
//                            imgOne, titleOne, infoOne, food?.get(0)?.imageUrl,
//                            food?.get(0)?.cuisine, food?.get(0)?.timeMinutes
//                        )
//                        bindViews(
//                            imgTwo, titleTwo, infoTwo, food?.get(1)?.imageUrl,
//                            food?.get(1)?.cuisine, food?.get(1)?.timeMinutes
//                        )
//                        bindViews(
//                            imgThree, titleThree, infoThree, food?.get(2)?.imageUrl,
//                            food?.get(2)?.cuisine, food?.get(2)?.timeMinutes
//                        )
//                        bindViews(
//                            imgFour, titleFour, infoFour, food?.get(3)?.imageUrl,
//                            food?.get(3)?.cuisine, food?.get(3)?.timeMinutes
//                        )
//                    }
//                }
//
//                Constants.KASHMIRI -> {
//                    food = result.getParcelableArrayList(Constants.KASHMIRI)!!
//                    binding.apply {
//                        bindViews(
//                            imgOne, titleOne, infoOne, food?.get(0)?.imageUrl,
//                            food?.get(0)?.cuisine, food?.get(0)?.timeMinutes
//                        )
//                        bindViews(
//                            imgTwo, titleTwo, infoTwo, food?.get(1)?.imageUrl,
//                            food?.get(1)?.cuisine, food?.get(1)?.timeMinutes
//                        )
//                        bindViews(
//                            imgThree, titleThree, infoThree, food?.get(2)?.imageUrl,
//                            food?.get(2)?.cuisine, food?.get(2)?.timeMinutes
//                        )
//                        bindViews(
//                            imgFour, titleFour, infoFour, food?.get(3)?.imageUrl,
//                            food?.get(3)?.cuisine, food?.get(3)?.timeMinutes
//                        )
//                    }
//                }
//            }

            }
        }
    }

    private fun bindViews(
        imageView: ImageView, textView: TextView, textView2: TextView,
        url: String?, title: String?, info: Int?
    ) {
        imageView.loadImage(url!!)
        textView.text = title
        textView2.text = info.toString()
    }

    override fun onClicks() {
        binding.kitchenToolbar.setNavigationOnClickListener {
            onBack()
        }
    }

}