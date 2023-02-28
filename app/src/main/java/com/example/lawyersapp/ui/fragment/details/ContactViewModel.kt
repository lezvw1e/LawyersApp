package com.example.lawyersapp.ui.fragment.details

import androidx.lifecycle.viewModelScope
import com.example.lawyersapp.core.BaseViewModel
import com.example.lawyersapp.core.utils.Resource
import com.example.lawyersapp.domain.admin.entity.AdminCreateEntity
import com.example.lawyersapp.domain.admin.use_case.AddAdminUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val addAdminUseCase: AddAdminUseCase
): BaseViewModel() {

    private val _addAdmin = MutableStateFlow<Resource<AdminCreateEntity>>(Resource.Loading())
    val addAdmin = _addAdmin.asStateFlow()

    fun addAdmin(model: AdminCreateEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            addAdminUseCase.addAdmin(model).collect {
                when (it) {
                    is Resource.Loading -> {
                        _addAdmin.value = Resource.Loading()
                    }
                    is Resource.Error -> {
                        _addAdmin.value = Resource.Error(it.message?: "Unknown error!!!")
                    }
                    is Resource.Success -> {
                    }
                }
            }
        }
    }


}