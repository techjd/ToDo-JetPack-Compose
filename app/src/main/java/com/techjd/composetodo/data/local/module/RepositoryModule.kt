package com.techjd.composetodo.data.local.module

import com.techjd.composetodo.data.local.dao.ToDoDao
import com.techjd.composetodo.data.local.repository.ToDoRepositoryImpl
import com.techjd.composetodo.domain.repository.ToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesToDoRepository(toDoDao: ToDoDao): ToDoRepository {
        return ToDoRepositoryImpl(toDoDao)
    }
}