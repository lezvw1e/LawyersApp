package com.example.lawyersapp.ui.fragment.document.check_document

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lawyersapp.databinding.FragmentCheckDocumentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CheckDocumentFragment : BottomSheetDialogFragment() {

    private val binding: FragmentCheckDocumentBinding by lazy {
        FragmentCheckDocumentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtDosudebnoe.setOnClickListener {
            initIntent("https://drive.google.com/uc?export=download&id=1SR73Q5SRbhzCXsOHnZfYZZmimDv0X1Do")
        }

        binding.txtObrashenie.setOnClickListener {
            initIntent("https://drive.google.com/uc?export=download&id=1i8rM2csJ0uozwC2-g_40M4lS4MvfShSA")
        }

        binding.txtRastarjenie.setOnClickListener {
            initIntent("https://drive.google.com/uc?export=download&id=15x0ViW_vasJyDo5Cn-u_R2kpBVjNQKid")
        }
    }

    @SuppressLint("IntentReset")
    private fun initIntent(number: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.type = "application/pdf"
        intent.data = Uri.parse(number)
        startActivity(intent)
    }
}