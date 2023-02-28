package com.example.lawyersapp.data.utils.mapper

import com.example.lawyersapp.core.BaseMapper
import com.example.lawyersapp.data.services.dto.ServicesDto
import com.example.lawyersapp.domain.services.entity.ServicesEntity

class ServicesDtoToEntity: BaseMapper<ServicesDto, ServicesEntity> {
    override fun invoke(p1: ServicesDto): ServicesEntity = ServicesEntity.onCreate(
        p1.id, p1.name, p1.desc
    )
}