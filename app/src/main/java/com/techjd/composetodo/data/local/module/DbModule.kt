package com.techjd.composetodo.data.local.module

import android.content.Context
import androidx.room.Room
import com.techjd.composetodo.data.local.dao.ToDoDao
import com.techjd.composetodo.data.local.database.ToDoListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun providesRoomDb(@ApplicationContext context: Context): ToDoListDatabase {
        return Room.databaseBuilder(
            context,
            ToDoListDatabase::class.java,
            "todo_list"
        ).build()
    }

    @Provides
    @Singleton
    fun provideToDoListDao(toDoListDatabase: ToDoListDatabase): ToDoDao {
        return toDoListDatabase.toDo
    }
}