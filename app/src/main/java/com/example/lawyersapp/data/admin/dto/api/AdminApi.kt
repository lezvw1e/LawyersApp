package com.example.lawyersapp.data.admin.dto.api

import com.example.lawyersapp.data.admin.dto.AdminCreateDto
import com.example.lawyersapp.data.admin.dto.AdminDto
import retrofit2.http.*

interface AdminApi {

    @GET("contacts")
    suspend fun getAdmin() : List<AdminDto>

    @POST("contacts/create/")
    suspend fun addAdmin(@Body model: AdminCreateDto): AdminCreateDto

}