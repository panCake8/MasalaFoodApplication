package com.example.masalafoodapplication.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.masalafoodapplication.databinding.FragmentFavouriteBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.favourite.adapter.FavouriteAdapter
import com.example.masalafoodapplication.ui.favourite.adapter.FavouriteListener


class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>(), FavouriteListener {
    private lateinit var adapter: FavouriteAdapter
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavouriteBinding
        get() = FragmentFavouriteBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    fun setup() {
        adapter = FavouriteAdapter(dataManager.getAllFavouriteFood(), this)
        binding.favRecycle.adapter = adapter
    }

    override fun onClickHeart(position: Int) {
        dataManager.deleteFavourite(position)
        adapter.setData(dataManager.getAllFavouriteFood())
    }

}