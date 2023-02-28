package com.example.lawyersapp.domain.services.repository

import com.example.lawyersapp.core.BaseResult
import com.example.lawyersapp.core.utils.Resource
import com.example.lawyersapp.domain.services.entity.ServicesEntity
import kotlinx.coroutines.flow.Flow

interface ServicesRepository {

    fun getServices(language: String) : Flow<BaseResult<List<ServicesEntity>, String>>
}