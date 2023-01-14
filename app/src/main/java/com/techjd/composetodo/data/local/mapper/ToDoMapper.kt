package com.techjd.composetodo.data.local.mapper

import com.techjd.composetodo.data.local.model.ToDoEntity
import com.techjd.composetodo.domain.models.ToDo

fun ToDoEntity.toToDo(): ToDo {
    return ToDo(
        id = this.id,
        task = this.task,
        isImp = this.isImp,
        isCompleted = this.isCompleted
    )
}

fun ToDo.toToDoEntity(): ToDoEntity {
    return ToDoEntity(
        id = this.id,
        task = this.task,
        isImp = this.isImp,
        isCompleted = this.isCompleted
    )
}