package com.example.masalafoodapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentSearchBinding


class SearchFragment : BaseFragment<com.example.masalafoodapplication.databinding
.FragmentSearchBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    override fun setup() {

    }

    override fun onClicks() {
        binding.searchBar.addTextChangedListener { text ->

            val list = DataManager.getAllFood().filter {
                it.Cuisine == text.toString() || it.recipeName == text.toString() || it.cleaned == text.toString()
            }
            Glide.with(requireContext()).load(list[0].imageUrl).into(binding.imvCity1)
        }
    }

}