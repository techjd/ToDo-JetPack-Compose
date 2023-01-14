package com.techjd.composetodo.presentation.screens


sealed class ToDoEvents {
    object AddToDo: ToDoEvents()
    data class TextChange(val text: String): ToDoEvents()
    data class IsImportantChange(val boolean: Boolean): ToDoEvents()
}
