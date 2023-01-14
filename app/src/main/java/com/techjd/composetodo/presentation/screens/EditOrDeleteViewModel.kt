package com.techjd.composetodo.presentation.screens

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techjd.composetodo.domain.models.ToDo
import com.techjd.composetodo.domain.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditOrDeleteViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository
): ViewModel() {

    var text = mutableStateOf("")

    val isChecked = mutableStateOf(false)

    val taskComplete = mutableStateOf(false)

    private val _singleToDo: MutableStateFlow<ToDo> = MutableStateFlow(ToDo(0,"",false, false))

    private val _uiEvent: MutableSharedFlow<UiEvent> = MutableSharedFlow()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    fun onEvent(editOrDeleteEvents: EditOrDeleteEvents) {
        when(editOrDeleteEvents) {
            is EditOrDeleteEvents.DeleteToDo -> {
                deleteToDo(_singleToDo.value)
            }
            is EditOrDeleteEvents.EditToDo -> {
                if (text.value.isNullOrEmpty()) {

                    sendUiEvent(UiEvent.ShowSnackBar("Text Cannot Be Empty"))
                } else {
                    Log.d("EVENT ", "onEvent: EXECUTED")
                    editToDo(
                        ToDo(
                            id = _singleToDo.value.id,
                            task = text.value,
                            isImp = isChecked.value,
                            isCompleted = taskComplete.value
                        )
                    )
                }
            }
            is EditOrDeleteEvents.IsCompletedChange -> {
                taskComplete.value = editOrDeleteEvents.isCompleted
            }
            is EditOrDeleteEvents.IsImportantChange -> {
                taskComplete.value = editOrDeleteEvents.isImp
            }
            is EditOrDeleteEvents.TextChange -> {
                text.value = editOrDeleteEvents.text
            }
        }
    }

    private fun editToDo(toDo: ToDo) {
        viewModelScope.launch {
            toDoRepository.addToDo(toDo)
            sendUiEvent(UiEvent.ShowSnackBar("To Do Updated"))
        }
    }

    suspend fun getSingleToDo(id: Int) {
        viewModelScope.launch {
            _singleToDo.value = toDoRepository.getSingleToDo(id)
            text.value = _singleToDo.value.task
            isChecked.value = _singleToDo.value.isImp
            taskComplete.value = _singleToDo.value.isCompleted

            Log.d(" EMPTY ", "getSingleToDo: ${_singleToDo.value}")
        }
    }

    private fun deleteToDo(toDo: ToDo) {
        viewModelScope.launch {
            toDoRepository.deleteToDo(toDo)
            sendUiEvent(UiEvent.PopBackStack)
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(uiEvent)
        }
    }

}