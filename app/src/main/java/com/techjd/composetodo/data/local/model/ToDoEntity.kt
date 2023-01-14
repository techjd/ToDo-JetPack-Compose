package com.techjd.composetodo.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todoList")
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "task")
    val task: String,
    @ColumnInfo(name = "isImp")
    val isImp: Boolean,
    @ColumnInfo(name = "isCompleted")
    val isCompleted: Boolean
)
