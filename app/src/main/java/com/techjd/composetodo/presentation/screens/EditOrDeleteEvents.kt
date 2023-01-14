package com.techjd.composetodo.presentation.screens

import com.techjd.composetodo.domain.models.ToDo

sealed class EditOrDeleteEvents {
    object EditToDo: EditOrDeleteEvents()
    object DeleteToDo: EditOrDeleteEvents()
    data class TextChange(val text: String): EditOrDeleteEvents()
    data class IsImportantChange(val isImp: Boolean): EditOrDeleteEvents()
    data class IsCompletedChange(val isCompleted: Boolean): EditOrDeleteEvents()
}
