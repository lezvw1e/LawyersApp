package com.example.lawyersapp.domain.admin.use_case

import com.example.lawyersapp.domain.admin.entity.AdminCreateEntity
import com.example.lawyersapp.domain.admin.repository.AdminRepository
import javax.inject.Inject

class AddAdminUseCase @Inject constructor(
    private val repo: AdminRepository
){

    suspend fun addAdmin(model: AdminCreateEntity) = repo.addAdmin(model)
}