package com.example.lawyersapp.data.admin.module

import com.example.lawyersapp.data.admin.repository.AdminRepositoryImpl
import com.example.lawyersapp.domain.admin.repository.AdminRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AdminModule {

    @Binds
    fun bindAdminRepo(
        adminRepositoryImpl: AdminRepositoryImpl
    ) : AdminRepository
}