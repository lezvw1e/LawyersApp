package com.example.lawyersapp.domain.admin.repository

import com.example.lawyersapp.core.utils.Resource
import com.example.lawyersapp.domain.admin.entity.AdminCreateEntity
import com.example.lawyersapp.domain.admin.entity.AdminEntity
import kotlinx.coroutines.flow.Flow

interface AdminRepository {

    suspend fun getAdmin(): Flow<Resource<List<AdminEntity>>>

    suspend fun addAdmin(model: AdminCreateEntity): Flow<Resource<AdminCreateEntity>>

}