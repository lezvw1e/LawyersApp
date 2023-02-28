package com.example.lawyersapp.domain.admin.use_case

import com.example.lawyersapp.domain.admin.repository.AdminRepository
import javax.inject.Inject

class GetAdminUseCase @Inject constructor(
    private val repo: AdminRepository
) {

    suspend fun getAdmin() = repo.getAdmin()

}