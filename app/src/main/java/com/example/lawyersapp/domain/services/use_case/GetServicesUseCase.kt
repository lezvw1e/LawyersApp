package com.example.lawyersapp.domain.services.use_case

import com.example.lawyersapp.core.BaseResult
import com.example.lawyersapp.domain.services.entity.ServicesEntity
import com.example.lawyersapp.domain.services.repository.ServicesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetServicesUseCase @Inject constructor(
    private val repo: ServicesRepository
) {

    fun getServices(language: String): Flow<BaseResult<List<ServicesEntity>, String>> =
        repo.getServices(language)

}