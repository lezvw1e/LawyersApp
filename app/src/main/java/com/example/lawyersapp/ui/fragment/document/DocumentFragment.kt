package com.example.lawyersapp.ui.fragment.document

import androidx.navigation.fragment.findNavController
import com.example.lawyersapp.R
import com.example.lawyersapp.core.BaseFragment
import com.example.lawyersapp.databinding.FragmentDocumentBinding

class DocumentFragment : BaseFragment<FragmentDocumentBinding>(FragmentDocumentBinding::inflate) {

    private lateinit var adapter: DocumentAdapter

    override fun setupUI() {
        initAdapter()
        initBtn()
    }

    private fun initBtn() {
        requireBinding().btnDocument.setOnClickListener {
            findNavController().navigate(R.id.checkDocumentFragment)
        }
    }

    private fun initAdapter() {
        adapter = DocumentAdapter()
        requireBinding().vpDocument.adapter = adapter
    }

}