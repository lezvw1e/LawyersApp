package com.example.lawyersapp.ui.fragment.admin

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.example.lawyersapp.core.BaseFragment
import com.example.lawyersapp.core.utils.UIState
import com.example.lawyersapp.databinding.FragmentAdminBinding
import com.example.lawyersapp.domain.admin.entity.AdminEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AdminFragment : BaseFragment<FragmentAdminBinding>(FragmentAdminBinding::inflate) {

    private val viewModel: AdminViewModel by viewModels()
    private val adapter: AdminAdapter by lazy {
        AdminAdapter()
    }

    override fun setupUI() {
        initView()
        swipe()
    }

    private fun swipe() {
        requireBinding().srlContainer.setOnRefreshListener {
            initView()
            requireBinding().srlContainer.isRefreshing = false
        }
    }

    private fun initView() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.admin.collect {
                    when (it) {
                        is UIState.Success -> {
                            renderList(it.data)
                        }
                        is UIState.Loading -> {

                        }
                        is UIState.Error -> {
                            Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(model: List<AdminEntity>) {
        adapter.addAdmin(model)
        requireBinding().rvAdmin.adapter = adapter
    }
}