package com.example.lawyersapp.ui.fragment.main

import androidx.lifecycle.viewModelScope
import com.example.lawyersapp.App
import com.example.lawyersapp.domain.lawyers.entity.LawyersEntity
import com.example.lawyersapp.domain.lawyers.use_case.GetLawyersUseCase
import com.example.lawyersapp.core.*
import com.example.lawyersapp.domain.services.entity.ServicesEntity
import com.example.lawyersapp.domain.services.use_case.GetServicesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLawyersUseCase: GetLawyersUseCase,
    private val getServicesUseCase: GetServicesUseCase
) : BaseViewModel() {

    private val _lawyersResult: MutableStateResult<List<LawyersEntity>, String> = MutableStateFlow(
        PendingResult
    )
    val lawyersResult: StateResult<List<LawyersEntity>, String> get() = _lawyersResult


    private val _servicesResult: MutableStateResult<List<ServicesEntity>, String> = MutableStateFlow(
        PendingResult
    )
    val servicesResult: StateResult<List<ServicesEntity>, String> get() = _servicesResult


    init {
        when (App.prefs.getString("score", "")) {
            "" -> {
                fetchServices("Services")
                fetchLawyers("lawyers")
            }
            "uz" -> {
                fetchServices("services_uz")
                fetchLawyers("lawyers_uz")
            }
            "en" -> {
                fetchServices("services_eng")
                fetchLawyers("lawyers_en")
            }
            "ky" -> {
                fetchServices("services_kyr")
                fetchLawyers("lawyers_kyr")
            }
        }
    }

    private fun fetchLawyers(lawyers: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getLawyersUseCase.getLawyers(lawyers)
                .onStart {
                    _lawyersResult.value = PendingResult
                }
                .catch {
                    _lawyersResult.value = ErrorResult(it.message.toString())
                }
                .collect {
                    _lawyersResult.value = it
                }
        }
    }

    private fun fetchServices(language: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getServicesUseCase.getServices(language)
                .onStart {
                    _servicesResult.value = PendingResult
                }
                .catch {
                    _servicesResult.value = ErrorResult(it.message.toString())
                }
                .collect {
                    _servicesResult.value = it
                }
        }
    }

    fun tryAgain() {
        when (App.prefs.getString("score", "")) {
            "" -> {
                fetchServices("Services")
                fetchLawyers("lawyers")
            }
            "uz" -> {
                fetchServices("services_uz")
                fetchLawyers("lawyers_uz")
            }
            "en" -> {
                fetchServices("services_eng")
                fetchLawyers("lawyers_en")
            }
            "ky" -> {
                fetchServices("services_kyr")
                fetchLawyers("lawyers_kyr")
            }
        }
    }
}