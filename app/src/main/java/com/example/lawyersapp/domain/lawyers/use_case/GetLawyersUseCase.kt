package com.example.lawyersapp.domain.lawyers.use_case

import com.example.lawyersapp.core.BaseResult
import com.example.lawyersapp.domain.lawyers.entity.LawyersEntity
import com.example.lawyersapp.domain.lawyers.repository.LawyersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLawyersUseCase @Inject constructor(
    private val repo: LawyersRepository
) {

    suspend fun getLawyers(lawyers: String): Flow<BaseResult<List<LawyersEntity>, String>> =
        repo.getLawyers(lawyers)
}