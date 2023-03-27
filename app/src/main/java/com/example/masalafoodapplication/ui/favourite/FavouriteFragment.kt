package com.example.masalafoodapplication.ui.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentFavouriteBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.favourite.adapter.FavouriteAdapter
import com.example.masalafoodapplication.ui.favourite.adapter.FavouriteListener


class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>(), FavouriteListener {
    private lateinit var adapter: FavouriteAdapter
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavouriteBinding
        get() = FragmentFavouriteBinding::inflate


    override fun setup() {
        adapter = FavouriteAdapter(DataManager.getAllFavouriteFood(), this)
        binding.favRecycle.adapter = adapter
    }

    override fun onClickHeart(position: Int) {
        DataManager.deleteFavourite(position)
        adapter.setData(DataManager.getAllFavouriteFood())
    }

}