package com.techjd.composetodo.data.local.repository

import com.techjd.composetodo.data.local.dao.ToDoDao
import com.techjd.composetodo.data.local.mapper.toToDo
import com.techjd.composetodo.data.local.mapper.toToDoEntity
import com.techjd.composetodo.domain.models.ToDo
import com.techjd.composetodo.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(
    private val toDoDao: ToDoDao
): ToDoRepository {

    override suspend fun addToDo(toDo: ToDo) {
        toDoDao.insertToDo(toDo.toToDoEntity())
    }

    override fun getToDos(): Flow<List<ToDo>> {
        return flow {
           toDoDao.getAllToDos().collect { todoList ->
               emit(todoList.map {
                   it.toToDo()
               })
           }
        }
    }

    override suspend fun getSingleToDo(id: Int): ToDo {
        return toDoDao.getToDo(id).toToDo()
    }

    override suspend fun editToDo(toDo: ToDo) {
        toDoDao.insertToDo(toDo.toToDoEntity())
    }

    override suspend fun deleteToDo(toDo: ToDo) {
        toDoDao.deleteToDo(toDo.toToDoEntity())
    }

    override fun getCompletedToDoCnt(): Flow<Int> {
        return toDoDao.getProgress(true)
    }
}