package com.example.lawyersapp.ui.fragment.details

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.lawyersapp.R
import com.example.lawyersapp.core.BaseFragment
import com.example.lawyersapp.databinding.FragmentDetailsBinding

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {


    override fun setupUI() {
        initBundle()
    }

    override fun setupObservers() {
        super.setupObservers()
    }

    private fun initBundle() {
        if (arguments != null) {
            val id = arguments?.getString("id")
            val name = arguments?.getString("name")
            val work = arguments?.getString("work")
            val special = arguments?.getString("special")
            val image = arguments?.getString("image")
            val phoneNumber = arguments?.getString("phone_number")!!


            requireBinding().txtLawyerName.text = name
            requireBinding().txtSpecial.text = special
            Glide.with(requireContext()).load(image).into(requireBinding().imgLawyer)
            requireBinding().txtWorkExperiences.text = work

            requireBinding().btnToContact.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("phone", phoneNumber)
                findNavController().navigate(R.id.contactFragment, bundle)
            }

        }
    }
}