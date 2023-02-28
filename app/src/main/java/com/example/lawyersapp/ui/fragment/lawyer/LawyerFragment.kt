package com.example.lawyersapp.ui.fragment.lawyer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.lawyersapp.core.BaseFragment
import com.example.lawyersapp.core.BaseResult
import com.example.lawyersapp.core.utils.onTryAgainListener
import com.example.lawyersapp.core.utils.renderSimpleResult
import com.example.lawyersapp.core.utils.showToast
import com.example.lawyersapp.R
import com.example.lawyersapp.databinding.FragmentLawyerBinding
import com.example.lawyersapp.domain.lawyers.entity.LawyersEntity
import com.example.lawyersapp.ui.fragment.lawyer.lawyarsAdapter.LawyersAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LawyerFragment : BaseFragment<FragmentLawyerBinding>(FragmentLawyerBinding::inflate),
LawyersAdapter.Results{

    private val adapter : LawyersAdapter by lazy {
        LawyersAdapter(this)
    }

    private val viewModel by viewModels<LawyersViewModel>()

    override fun setupUI() {
        initAdapters()
        initBtn()
    }

    private fun initBtn() {
        onTryAgainListener(requireView()) {
            viewModel.tryAgain()
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        observeLawyer()
    }

    private fun observeLawyer() {
        viewModel.lawyersResult.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach {
                handleResult(it)
            }
            .launchIn(lifecycleScope)
    }

    private fun handleResult(result: BaseResult<List<LawyersEntity>, String>) {
        renderSimpleResult(requireBinding().root,
        result,
        onSuccess = { data ->
            handleRibbon(data)
        },
        onError = { msg ->
            handleError(msg)
        })
    }

    private fun handleError(msg: String?) {
        requireContext().showToast(msg)
    }

    private fun handleRibbon(data: List<LawyersEntity>) {
        adapter.list = data
    }

    private fun initAdapters() {
        requireBinding().rvLawyer.adapter = adapter
    }

    override fun onClickListener(
        id: String,
        name: String,
        work: String,
        image: String,
        special: String,
        phone_number: String
    ) {
        val bundle = Bundle()
        bundle.putString("id", id)
        bundle.putString("name", name)
        bundle.putString("work", work)
        bundle.putString("image", image)
        bundle.putString("special", special)
        bundle.putString("phone_number", phone_number)
        findNavController().navigate(R.id.detailsFragment, bundle)
    }
}