package com.techjd.composetodo.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.techjd.composetodo.R
import com.techjd.composetodo.presentation.components.ToDoItem
import com.techjd.composetodo.presentation.screens.*
import com.techjd.composetodo.presentation.ui.theme.ComposeToDoTheme
import com.techjd.composetodo.presentation.ui.theme.fontFamilty
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeToDoTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "main_screen"
                ) {
                    composable("main_screen") {
                        MainScreen(
                            onNewToDoClick = { navController.navigate("add_to_do") },
                            navigate = { id -> navController.navigate("edit_delete_to_do/${id}") },
                        )
                    }
                    composable("add_to_do") {
                        AddNewToDoScreen {
                            navController.popBackStack()
                        }
                    }
                    composable("edit_delete_to_do/{toDoId}") { backStackEntry ->

                        val id = backStackEntry.arguments?.getString("toDoId")!!
                        Log.d("id ", "onCreate: id ${id}")

                        EditOrDeleteToDoScreen(
                            id = id.toInt(),
                        ) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}