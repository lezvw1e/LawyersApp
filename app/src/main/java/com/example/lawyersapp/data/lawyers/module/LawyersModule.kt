package com.example.lawyersapp.data.lawyers.module

import com.example.lawyersapp.data.lawyers.repository.LawyersRepositoryImpl
import com.example.lawyersapp.domain.lawyers.repository.LawyersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface LawyersModule {

    @Binds
    fun bindLawyersRepo(
        lawyersRepositoryImpl: LawyersRepositoryImpl
    ) : LawyersRepository


}