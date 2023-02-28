package com.example.lawyersapp.data.admin.dto

import com.example.lawyersapp.domain.admin.entity.AdminEntity

data class AdminDto(
    val id: Int,
    val name: String,
    val surname: String,
    val phone: String,
    val created: String
)
fun AdminDto.DtoToEntity() : AdminEntity {
    return AdminEntity(
        id = id,
        name = name,
        surname = surname,
        phone = phone,
        created = created
    )
}
