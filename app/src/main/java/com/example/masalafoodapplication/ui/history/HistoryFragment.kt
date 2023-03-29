package com.example.masalafoodapplication.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.masalafoodapplication.databinding.FragmentHistoryBinding
import com.example.masalafoodapplication.ui.base.BaseFragment


class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHistoryBinding
        get() = FragmentHistoryBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClicks()
    }


    fun onClicks() {
        binding.historyToolbar.setNavigationOnClickListener {
            onBack()
        }

    }

}