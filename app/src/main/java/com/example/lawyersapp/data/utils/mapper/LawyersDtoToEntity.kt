package com.example.lawyersapp.data.utils.mapper

import com.example.lawyersapp.core.BaseMapper
import com.example.lawyersapp.data.lawyers.dto.LawyersDto
import com.example.lawyersapp.domain.lawyers.entity.LawyersEntity
    
class LawyersDtoToEntity : BaseMapper<LawyersDto, LawyersEntity> {
    override fun invoke(p1: LawyersDto): LawyersEntity = LawyersEntity.onCreate(
        p1.id, p1.name, p1.work_experience, p1.image, p1.special, p1.phone_number
    )
}