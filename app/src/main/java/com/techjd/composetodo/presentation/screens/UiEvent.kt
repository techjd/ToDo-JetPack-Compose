package com.techjd.composetodo.presentation.screens

sealed class UiEvent() {
    object Idle: UiEvent()
    data class ShowSnackBar(val content: String): UiEvent()
    object PopBackStack: UiEvent()
}
