package com.example.lawyersapp.ui.fragment.lawyer

import androidx.lifecycle.viewModelScope
import com.example.lawyersapp.App
import com.example.lawyersapp.domain.lawyers.entity.LawyersEntity
import com.example.lawyersapp.domain.lawyers.use_case.GetLawyersUseCase
import com.example.lawyersapp.core.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LawyersViewModel @Inject constructor(
    private val getLawyersUseCase: GetLawyersUseCase
): BaseViewModel() {

    private val _lawyersResult: MutableStateResult<List<LawyersEntity>, String> =
        MutableStateFlow(PendingResult)
    val lawyersResult: StateResult<List<LawyersEntity>, String> get() = _lawyersResult

    init {
        when (App.prefs.getString("score", "")) {
            "" -> {
                fetchLawyers("lawyers")
            }
            "uz" -> {
                fetchLawyers("lawyers_uz")
            }
            "en" -> {
                fetchLawyers("lawyers_en")
            }
            "ky" -> {
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

    fun tryAgain() {
        when (App.prefs.getString("score", "")) {
            "" -> {
                fetchLawyers("lawyers")
            }
            "uz" -> {
                fetchLawyers("lawyers_uz")
            }
            "en" -> {
                fetchLawyers("lawyers_en")
            }
            "ky" -> {
                fetchLawyers("lawyers_kyr")
            }
        }
    }


}