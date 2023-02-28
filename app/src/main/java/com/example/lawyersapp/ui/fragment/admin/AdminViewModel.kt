package com.example.lawyersapp.ui.fragment.admin

import androidx.lifecycle.viewModelScope
import com.example.lawyersapp.core.BaseViewModel
import com.example.lawyersapp.core.utils.Resource
import com.example.lawyersapp.core.utils.UIState
import com.example.lawyersapp.domain.admin.entity.AdminEntity
import com.example.lawyersapp.domain.admin.use_case.GetAdminUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val getAdminUseCase: GetAdminUseCase
) : BaseViewModel() {

    private val _admin = MutableStateFlow<UIState<List<AdminEntity>>>(UIState.Loading())
    val admin = _admin.asStateFlow()

    init {
        getAdmin()
    }

    private fun getAdmin() {
        viewModelScope.launch(Dispatchers.IO) {
            getAdminUseCase.getAdmin().collect {
                when (it) {
                    is Resource.Error -> {
                        _admin.value = UIState.Error(it.message?: "Unknown error")
                    }
                    is Resource.Loading -> {
                        _admin.value = UIState.Loading()
                    }
                    is Resource.Success -> {
                        _admin.value = UIState.Success(it.data!!)
                    }
                }

            }
        }
    }
}