package com.example.masalafoodapplication.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.databinding.ItemBannerBinding
import com.example.masalafoodapplication.ui.base.BaseAdapter
import com.example.masalafoodapplication.util.loadImage


class BannersAdapter(
    items: List<String>,
) :
    BaseAdapter<String, ItemBannerBinding>(items) {
    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemBannerBinding {
        return ItemBannerBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemBannerBinding, item: String) {
        binding.imageBanner.loadImage(item)
    }
}