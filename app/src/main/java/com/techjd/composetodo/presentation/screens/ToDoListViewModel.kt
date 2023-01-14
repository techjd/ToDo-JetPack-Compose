package com.techjd.composetodo.presentation.screens

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techjd.composetodo.domain.models.ToDo
import com.techjd.composetodo.domain.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository
): ViewModel() {
    val todoList = toDoRepository.getToDos()
    val cnt = toDoRepository.getCompletedToDoCnt()
}