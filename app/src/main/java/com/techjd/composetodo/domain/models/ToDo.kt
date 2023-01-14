package com.techjd.composetodo.domain.models

data class ToDo(
    val id: Int,
    val task: String,
    val isImp: Boolean,
    val isCompleted: Boolean
)
