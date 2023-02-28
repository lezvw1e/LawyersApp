package com.example.lawyersapp.data.services.dto

import com.example.lawyersapp.domain.services.entity.ServicesEntity

data class ServicesDto(
    var id: String = "",
    var name: String = "",
    var desc: String = ""
)
fun ServicesDto.toDomain() = ServicesEntity(
    id = id,
    name = name,
    desc = desc
)
