package com.example.masalafoodapplication.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.viewbinding.ViewBinding
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.models.Food

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        onClicks()
//            requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(false) {
//                override fun handleOnBackPressed() {
//                    Log.d("TAG","adjasdjsa")
//                }
//
//            })
    }

    abstract fun setup()
    open fun onClicks() {}

    fun transitionTo(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            setReorderingAllowed(true)
        }
    }

    fun transitionToWithBackStack(fragment: Fragment, tag: String) {
        parentFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(tag)
            setReorderingAllowed(true)
        }
    }

    fun transitionToWithBackStack2(fragment: Fragment, fragment2: Fragment, tag: String) {
        parentFragmentManager.commit {
            add(R.id.fragment_container, fragment)
            addToBackStack(tag)
            setReorderingAllowed(true)
                .hide(fragment2)
        }
    }

    fun onBack() {
        requireActivity().onBackPressed()
    }

    fun onBack(id: Int, tag: String) {
        newInstance(id, tag)
        requireActivity().onBackPressed()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun newInstance(int: Int, key: String) {
        val bundle = Bundle()
        bundle.putInt(key, int)
        parentFragmentManager.setFragmentResult(key, bundle)
    }

    fun newInstance(string: String, key: String) {
        val bundle = Bundle()
        bundle.putString(key, string)
        parentFragmentManager.setFragmentResult(key, bundle)
    }

}