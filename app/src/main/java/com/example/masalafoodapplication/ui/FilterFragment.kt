package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.databinding.FragmentFilterBinding


class FilterFragment : BaseFragment<FragmentFilterBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFilterBinding
        get() = FragmentFilterBinding::inflate


    override fun setup() {
    }

    override fun onClicks() {

    }

}