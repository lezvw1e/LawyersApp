package com.example.lawyersapp.data.admin.dto

import com.example.lawyersapp.domain.admin.entity.AdminCreateEntity

data class AdminCreateDto(
    val phone: String,
    val name: String,
    val surname:String
)
fun AdminCreateEntity.toAdminCreateDto() : AdminCreateDto {
    return AdminCreateDto(
        phone = phone,
        name = name,
        surname = surname
    )
}

fun AdminCreateDto.toDomain(): AdminCreateEntity {
    return AdminCreateEntity(
        phone = phone,
        name = name,
        surname = surname
    )
}