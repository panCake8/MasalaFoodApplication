package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.databinding.FragmentKitchensBinding

class KitchensFragment : BaseFragment<FragmentKitchensBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentKitchensBinding
        get() = FragmentKitchensBinding::inflate

    override fun setup() {

    }

    override fun onClicks() {

    }

}