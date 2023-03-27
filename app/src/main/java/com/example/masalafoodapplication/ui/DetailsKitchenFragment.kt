package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentDetailsKitchenBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.util.loadImage
import com.example.masalafoodapplication.ui.suggestion.adapters.FoodsAdapter
import com.example.masalafoodapplication.util.SuggestionOnClick

class DetailsKitchenFragment(val name: String) : BaseFragment<FragmentDetailsKitchenBinding>(),DetailsKitchenOnClick {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsKitchenBinding
        get() = FragmentDetailsKitchenBinding::inflate
    override fun setup() {

        val adapter=DetailsKitchenAdapter(DataManager.getAllFood(),this)
        binding.detailsRecyclerView.adapter=adapter
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

    override fun onClickListener(nameFood: String) {
        Toast.makeText(requireContext().applicationContext,nameFood,Toast.LENGTH_LONG).show()
    }

}