package com.techjd.composetodo.presentation.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techjd.composetodo.R
import com.techjd.composetodo.domain.models.ToDo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditOrDeleteToDoScreen(
    editOrDeleteViewModel: EditOrDeleteViewModel = hiltViewModel(),
    id: Int,
    onBack: () -> Unit,
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        Log.d("ID ", "EditOrDeleteToDoScreen: ${id}")
        editOrDeleteViewModel.getSingleToDo(id)

        editOrDeleteViewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Idle -> {
                    // Don't do anything
                }
                is UiEvent.PopBackStack -> {
                    onBack()
                }
                is UiEvent.ShowSnackBar -> {
                    // Showing SnackBar as of now
//                    Toast.makeText(context, event.content, Toast.LENGTH_SHORT).show()
                    onBack()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "EDIT TODO",
                        fontFamily = FontFamily(Font(R.font.papernotes)),
                        fontSize = 30.sp,
                        letterSpacing = 5.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBack()
                        }
                    ) {
                        Image(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                }
            )
        },
    ) { values ->
        Column(
            modifier = Modifier
                .padding(values)
        ) {
            TextField(
                value = editOrDeleteViewModel.text.value,
                onValueChange = { editOrDeleteViewModel.text.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                ,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFD7F8D4),
                    cursorColor = Color.DarkGray
                ),
                placeholder = {
                    Text(text = "Edit ToDo", fontFamily = FontFamily(Font(R.font.papernotes)), fontSize = 20.sp)
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily(
                        Font(R.font.papernotes),
                    ),
                    fontSize = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(5.dp))

            /* Is it Important  */

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = editOrDeleteViewModel.isChecked.value,
                    onCheckedChange = {
                        editOrDeleteViewModel.isChecked.value = it
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF96FF8D)
                    )
                )

                Text(
                    modifier = Modifier.clickable {
                        editOrDeleteViewModel.isChecked.value = !editOrDeleteViewModel.isChecked.value
                    },
                    text = "Is It Important ? ",
                    fontFamily = FontFamily(Font(R.font.papernotes)),
                    letterSpacing = 3.sp,
                    fontSize = 22.sp
                )
            }

            /* Is Task Completed ? */

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = editOrDeleteViewModel.taskComplete.value,
                    onCheckedChange = {
                        editOrDeleteViewModel.taskComplete.value = it
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF96FF8D)
                    )
                )

                Text(
                    modifier = Modifier.clickable {
                        editOrDeleteViewModel.taskComplete.value = !editOrDeleteViewModel.taskComplete.value
                    },
                    text = "Is Task Completed ? ",
                    fontFamily = FontFamily(Font(R.font.papernotes)),
                    letterSpacing = 3.sp,
                    fontSize = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            EditToDoButton {
                editOrDeleteViewModel.onEvent(EditOrDeleteEvents.EditToDo)
            }

            Spacer(modifier = Modifier.height(25.dp))

            DeleteToDoButton {
                editOrDeleteViewModel.onEvent(EditOrDeleteEvents.DeleteToDo)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditToDoButton(editToDo: () -> Unit) {
    Card(
        shape = RoundedCornerShape(120.dp),
        onClick = {
            editToDo()
        },
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "UPDATE TODO",
            modifier = Modifier
                .background(Color(0xFF96FF8D))
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            fontFamily = FontFamily(Font(R.font.papernotes)),
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            letterSpacing = 5.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteToDoButton(deleteToDo: () -> Unit) {
    Card(
        shape = RoundedCornerShape(120.dp),
        onClick = {
            deleteToDo()
        },
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "DELETE",
            modifier = Modifier
                .background(Color(0xFF96FF8D))
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            fontFamily = FontFamily(Font(R.font.papernotes)),
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            letterSpacing = 5.sp
        )
    }
}