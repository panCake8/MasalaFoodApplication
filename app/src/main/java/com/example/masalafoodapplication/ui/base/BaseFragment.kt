package com.example.masalafoodapplication.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

    abstract fun setup()
    open fun onClicks(){}

    fun transitionTo(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            setReorderingAllowed(true)
        }
    }

    fun transitionToWithBackStack(fragment: Fragment, name: String) {
        parentFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
                .addToBackStack(name)
            setReorderingAllowed(true)
        }
    }

    fun newInstance(food: ArrayList<Food>, name: String) {
        val bundle = Bundle()
        bundle.putParcelableArrayList(name, food)
        parentFragmentManager.setFragmentResult(name, bundle)
    }

    fun newInstance(food: Food, name: String) {
        val bundle = Bundle()
        bundle.putParcelable(name, food)
        parentFragmentManager.setFragmentResult(name, bundle)
    }

    fun newInstance(foodName: String, ingredient: String, value: String, name: String) {
        val bundle = Bundle()
        bundle.putString("foodName", foodName)
        bundle.putString("cleaned", ingredient)
        bundle.putString("value", value)
        parentFragmentManager.setFragmentResult(name, bundle)
    }

    fun onBack() {
        requireActivity().onBackPressed()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}