package com.techjd.composetodo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techjd.composetodo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddButton(addNewToDo: () -> Unit) {
    Card(
        shape = RoundedCornerShape(120.dp),
        onClick = {
            addNewToDo()
        }
    ) {
        Text(
            text = "ADD NEW TODO",
            modifier = Modifier
                .background(Color(0xFF96FF8D))
                .padding(15.dp),
            fontFamily = FontFamily(Font(R.font.papernotes)),
            fontSize = 20.sp
        )
    }
}