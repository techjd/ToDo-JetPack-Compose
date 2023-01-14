package com.techjd.composetodo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.techjd.composetodo.data.local.dao.ToDoDao
import com.techjd.composetodo.data.local.model.ToDoEntity

@Database(
    entities = [ToDoEntity::class],
    version = 1
)
abstract class ToDoListDatabase: RoomDatabase() {
    abstract val toDo: ToDoDao
}