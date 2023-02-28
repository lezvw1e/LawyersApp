package com.example.lawyersapp.data.services.module

import com.example.lawyersapp.data.services.repository.ServicesRepositoryImpl
import com.example.lawyersapp.domain.services.repository.ServicesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ServicesModule {

    @Binds
    fun bindServicesRepo(
        servicesRepositoryImpl: ServicesRepositoryImpl
    ) : ServicesRepository

}