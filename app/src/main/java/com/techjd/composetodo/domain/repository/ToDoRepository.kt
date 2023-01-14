package com.techjd.composetodo.domain.repository

import com.techjd.composetodo.domain.models.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    suspend fun addToDo(toDo: ToDo)
    fun getToDos(): Flow<List<ToDo>>
    suspend fun getSingleToDo(id: Int): ToDo
    suspend fun editToDo(toDo: ToDo)
    suspend fun deleteToDo(toDo: ToDo)
    fun getCompletedToDoCnt(): Flow<Int>
}