package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.masalafoodapplication.databinding.FragmentDetailsKitchenBinding


class DetailsKitchenFragment : BaseFragment<FragmentDetailsKitchenBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsKitchenBinding
        get() = FragmentDetailsKitchenBinding::inflate

    override fun setup() {
        parentFragmentManager.commit {
            add(binding.cardFive.id, DetailsKitchenFragment())
        }

    }

    override fun onClicks() {

    }

}