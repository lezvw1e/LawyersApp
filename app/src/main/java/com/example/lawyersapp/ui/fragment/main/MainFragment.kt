package com.example.lawyersapp.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.lawyersapp.App
import com.example.lawyersapp.core.BaseFragment
import com.example.lawyersapp.core.BaseResult
import com.example.lawyersapp.core.utils.onTryAgainListener
import com.example.lawyersapp.core.utils.renderSimpleResult
import com.example.lawyersapp.core.utils.showToast
import com.example.lawyersapp.R
import com.example.lawyersapp.databinding.FragmentMainBinding
import com.example.lawyersapp.domain.lawyers.entity.LawyersEntity
import com.example.lawyersapp.domain.services.entity.ServicesEntity
import com.example.lawyersapp.ui.fragment.main.adapter.LawyerAdapter
import com.example.lawyersapp.ui.fragment.main.adapter.ServicesAdapter
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate),
LawyerAdapter.Result{

    private val lawyerAdapter: LawyerAdapter by lazy { LawyerAdapter(this) }
    private val servicesAdapter: ServicesAdapter by lazy { ServicesAdapter() }
    private lateinit var mAuth: FirebaseAuth
    private val viewModel by viewModels<MainViewModel>()

    override fun setupUI() {
        mAuth = FirebaseAuth.getInstance()
        if (!App.prefs.getBoolean("key", false)) {
            findNavController().navigate(R.id.onBoardFragment)
            App.prefs.edit().putBoolean("key", true).apply()
        } else {
            if (mAuth.currentUser == null) {
                findNavController().navigateUp()
                findNavController().navigate(R.id.registerFragment)
            }
        }
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
        observeService()
        initControllers()
    }

    private fun observeService() {
        viewModel.servicesResult.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach {
                serviceResult(it)
            }
            .launchIn(lifecycleScope)
    }

    private fun serviceResult(it: BaseResult<List<ServicesEntity>, String>) {
        renderSimpleResult(requireBinding().root,
        it,
        onSuccess = { data ->
            serviceRibbon(data)
        },
        onError = { msg ->
            serviceError(msg)
        })
    }

    private fun serviceError(msg: String?) {
        requireContext().showToast(msg)
    }

    private fun serviceRibbon(data: List<ServicesEntity>) {
        servicesAdapter.list = data
    }

    private fun initControllers() {
        requireBinding().txtLwyersAll.setOnClickListener {
            findNavController().navigate(R.id.lawyerFragment)
        }
    }

    private fun observeLawyer() {
        viewModel.lawyersResult.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach {
                handleResult(it)
            }
            .launchIn(lifecycleScope)
    }

    private fun handleResult(result: BaseResult<List<LawyersEntity>, String>) {
        renderSimpleResult(
            requireBinding().root,
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
        lawyerAdapter.list = data
    }

    private fun initAdapters() {
        requireBinding().rvLawyers.adapter = lawyerAdapter
        requireBinding().rvServices.adapter = servicesAdapter
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