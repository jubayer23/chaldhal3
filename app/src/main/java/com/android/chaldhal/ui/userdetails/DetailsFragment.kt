package com.android.chaldhal.ui.userdetails

import AGE
import FIRST
import LAST
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.chaldhal.ui.base.BaseFragment
import com.android.chaldhal.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    private var firstName: String? = null
    private var lastName: String? = null
    private var age: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()


        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {

        age = arguments?.getInt(AGE)
        binding.age.text = "$age YEARS OLD"

        if (!arguments?.getString(FIRST).isNullOrEmpty() && !arguments?.getString(LAST).isNullOrEmpty()) {

            firstName = arguments?.getString(FIRST)
            lastName = arguments?.getString(LAST)


            binding.userNameHint.text = firstName?.substring(0, 1) + lastName?.substring(0, 1)
            binding.firstName.text = firstName + lastName

        } else {

            binding.userNameHint.text = firstName?.substring(0, 1)
            binding.firstName.text = firstName
        }
    }


    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentDetailsBinding.inflate(inflater, container, false)

}