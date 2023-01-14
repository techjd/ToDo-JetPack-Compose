package com.techjd.composetodo.presentation.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techjd.composetodo.R
import com.techjd.composetodo.domain.models.ToDo
import com.techjd.composetodo.presentation.components.AddButton
import com.techjd.composetodo.presentation.components.ToDoItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onNewToDoClick: () -> Unit,
    navigate: (id: Int) -> Unit,
    toDoListViewModel: ToDoListViewModel = hiltViewModel()
) {
    val data = toDoListViewModel.todoList.collectAsState(initial = emptyList())
    val cnt = toDoListViewModel.cnt.collectAsState(initial = 0)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "TODO LIST",
                            fontFamily = FontFamily(Font(R.font.papernotes)),
                            fontSize = 30.sp,
                            letterSpacing = 10.sp
                        )
                    }
                )
            },
            floatingActionButton = {
                AddButton(onNewToDoClick)
            }
        ) { value ->
            if (data.value.isEmpty()) {

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(value),
                    contentAlignment = Alignment.Center) {

                    Text(text = "Add Some To Do \n to Get Started ✨",
                        fontFamily = FontFamily(Font(R.font.papernotes)),
                        lineHeight = 50.sp,
                        fontSize = 35.sp,
                        letterSpacing = 5.sp
                    )
                }


            } else {
                Column(
                    modifier = Modifier.padding(value)
                ) {
                    Progress(
                        total = data.value.size,
                        completed = cnt.value
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    LazyColumn {
                        items(count = data.value.size) { idx ->
                            ToDoItem(id = data.value[idx].id, task = data.value[idx].task, isImp = data.value[idx].isImp, isCompleted = data.value[idx].isCompleted, navigate)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Progress(
    total: Int,
    completed: Int
) {
        val width = remember {
            mutableStateOf(completed.toFloat() / total.toFloat())
        }

        val percent = remember {
            mutableStateOf((completed.toFloat() / total.toFloat()) * 100)
        }

        Box(modifier = Modifier
            .padding(10.dp)
            .border(BorderStroke(1.dp, Color.LightGray), shape = CircleShape)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .fillMaxWidth()
                ) {
            Box(
                modifier = Modifier
                    .background(Color(0xFF96FF8D))
                    .fillMaxWidth(width.value)
                    .clip(RoundedCornerShape(15.dp))
                ,
                contentAlignment = Alignment.Center
            ) {
                Spacer(modifier = Modifier.heightIn(30.dp) )
            }
            Text(text = "${percent.value.toInt()} %", modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.papernotes),
                    ),
                fontSize = 20.sp
                )
        }
}