package com.ayoba.data.database

import com.ayoba.data.database.dao.ChatDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesTopicsDao(
        database: AyobaAppDatabase,
    ): ChatDao = database.chatDao()

}