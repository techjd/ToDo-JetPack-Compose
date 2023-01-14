package com.techjd.composetodo.data.local.dao

import androidx.room.*
import com.techjd.composetodo.data.local.model.ToDoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(toDoEntity: ToDoEntity)

    @Query("SELECT * FROM todoList")
    fun getAllToDos(): Flow<List<ToDoEntity>>

    @Query("SELECT * FROM todoList WHERE id = :todoId")
    suspend fun getToDo(todoId: Int): ToDoEntity

    @Delete
    suspend fun deleteToDo(toDoEntity: ToDoEntity)

    @Query("SELECT COUNT(id) FROM todoList WHERE isCompleted = :isCompleted")
    fun getProgress(isCompleted: Boolean): Flow<Int>
}