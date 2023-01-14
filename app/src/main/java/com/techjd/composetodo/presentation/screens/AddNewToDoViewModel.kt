package com.techjd.composetodo.presentation.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techjd.composetodo.domain.models.ToDo
import com.techjd.composetodo.domain.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewToDoViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository
): ViewModel() {

    val text = mutableStateOf("")

    val isChecked = mutableStateOf(false)

    private val _uiEvent: MutableSharedFlow<UiEvent> = MutableSharedFlow()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onEvent(events: ToDoEvents) {
        when(events) {
            is ToDoEvents.AddToDo -> {
                if (text.value.isNullOrEmpty()) {
                    sendUiEvent(UiEvent.ShowSnackBar("Text Cannot Be Empty"))
                } else {
                    addToDo(
                        ToDo(
                            id = 0,
                            task = text.value,
                            isImp = isChecked.value,
                            isCompleted = false
                        )
                    )
                    text.value = ""
                    isChecked.value = false
                }
            }
            is ToDoEvents.IsImportantChange -> {
                isChecked.value = events.boolean
            }
            is ToDoEvents.TextChange -> {
                text.value = events.text
            }
        }
    }
    private fun addToDo(toDo: ToDo) {
        viewModelScope.launch {
            toDoRepository.addToDo(toDo)
            sendUiEvent(UiEvent.ShowSnackBar("To Do Added"))
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(uiEvent)
        }
    }

}