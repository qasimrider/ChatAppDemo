package com.ayoba.chatapp.di

import com.ayoba.data.ChatRepository
import com.ayoba.data.ChatRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    fun bindsChatRepository(chatRepository: ChatRepositoryImpl): ChatRepository
}