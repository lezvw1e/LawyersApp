package com.example.lawyersapp.domain.lawyers.entity

data class LawyersEntity(
    val id: String,
    val name: String,
    val work_experience: String,
    val image: String,
    val special: String,
    val phone_number: String
) {

    companion object {
        fun onCreate(id: String,name: String, work_experience: String, image: String, special: String,
        phone_number: String)
        : LawyersEntity = LawyersEntity(
            id, name, work_experience, image, special, phone_number
        )
    }
}