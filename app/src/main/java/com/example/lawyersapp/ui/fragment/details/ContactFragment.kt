package com.example.lawyersapp.ui.fragment.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.lawyersapp.core.utils.Resource
import com.example.lawyersapp.databinding.FragmentContactBinding
import com.example.lawyersapp.domain.admin.entity.AdminCreateEntity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactFragment : BottomSheetDialogFragment() {

    private val binding: FragmentContactBinding by lazy {
        FragmentContactBinding.inflate(layoutInflater)
    }
    private val viewModel: ContactViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val phoneNumber = arguments?.getString("phone")!!
            binding.btnContact.setOnClickListener {
                initView()
//                val dial = "tel:$phoneNumber"
//                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(dial)))
                if (phoneNumber != "") {
                    val number = "https://wa.me/$phoneNumber?text=Hello!"
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(number)
                    startActivity(intent)
                } else {
                    makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                }
            }
        }
        navigateUp()
        initViewModel()
        whatsUp()
    }

    private fun whatsUp() {
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.addAdmin.collect {
                    when (it) {
                        is Resource.Loading -> {
                        }
                        is Resource.Error -> {
                        }
                        is Resource.Success -> {
                            Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun navigateUp() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initView() {
        val name = binding.edNameEd.text.toString().trim()
        val surname = binding.edSurnameEd.text.toString().trim()
        val phone = binding.edCellphoneEd.text.toString().trim()

        viewModel.addAdmin(AdminCreateEntity(phone, name, surname))
        findNavController().navigateUp()
    }
}