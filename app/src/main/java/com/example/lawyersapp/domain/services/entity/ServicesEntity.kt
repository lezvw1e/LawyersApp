package com.example.lawyersapp.domain.services.entity

data class ServicesEntity(
    val id: String,
    val name: String,
    val desc: String
) {
    companion object {
        fun onCreate(id: String, name: String, desc: String): ServicesEntity = ServicesEntity(
            id, name, desc
        )
    }
}

