package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.databinding.FragmentFavouriteBinding
import com.example.masalafoodapplication.ui.base.BaseFragment


class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavouriteBinding
        get() = FragmentFavouriteBinding::inflate



    override fun setup() {

    }

    override fun onClicks() {

    }

}