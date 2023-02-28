package com.example.lawyersapp.data.admin.repository

import android.util.Log
import com.example.lawyersapp.core.utils.Resource
import com.example.lawyersapp.data.admin.dto.DtoToEntity
import com.example.lawyersapp.data.admin.dto.api.AdminApi
import com.example.lawyersapp.data.admin.dto.toAdminCreateDto
import com.example.lawyersapp.data.admin.dto.toDomain
import com.example.lawyersapp.domain.admin.entity.AdminCreateEntity
import com.example.lawyersapp.domain.admin.entity.AdminEntity
import com.example.lawyersapp.domain.admin.repository.AdminRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class AdminRepositoryImpl @Inject constructor(
    private val api: AdminApi
): AdminRepository {

    override suspend fun getAdmin(): Flow<Resource<List<AdminEntity>>> = flow {
        try {
            emit(Resource.Loading())
            val list = api.getAdmin().map { it.DtoToEntity() }
            emit(Resource.Success(list))
        } catch (e: Exception) {
            emit(Resource.Error("$e"))
        } catch (e: IOException) {
            emit(Resource.Error("Error  io exception!!!"))
        }
    }

    override suspend fun addAdmin(model: AdminCreateEntity): Flow<Resource<AdminCreateEntity>> = flow {
        emit(Resource.Loading())
        try {
            val data = api.addAdmin(model.toAdminCreateDto()).toDomain()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error("$e"))
            Log.e("ololo", "addAdmin: $e")
        }
    }
}