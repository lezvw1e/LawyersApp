package com.example.lawyersapp.domain.lawyers.repository

import com.example.lawyersapp.core.BaseResult
import com.example.lawyersapp.domain.lawyers.entity.LawyersEntity
import kotlinx.coroutines.flow.Flow

interface LawyersRepository {

    suspend fun getLawyers(lawyers: String): Flow<BaseResult<List<LawyersEntity>, String>>
}