package com.techjd.composetodo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.techjd.composetodo.R
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techjd.composetodo.presentation.ui.theme.fontFamilty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoItem(
    id: Int,
    task: String,
    isImp: Boolean,
    isCompleted: Boolean,
    navigate: (id: Int) -> Unit
) {
    Card(
        modifier = Modifier.padding(10.dp),
        shape = RoundedCornerShape(7.dp),
        onClick = {
            navigate(id)
        }
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFFD7F8D4))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text (
                text = task,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .weight(1f)
                    .padding(10.dp),
                textAlign = TextAlign.Justify,
                fontFamily = FontFamily(Font(R.font.papernotes)),
                fontSize = 25.sp,
                style = TextStyle(textDecoration = if (isCompleted) TextDecoration.LineThrough else TextDecoration.None)
            )

            if (isImp) {
                Icon(
                    painter = painterResource(id = R.drawable.exclamation_mark),
                    contentDescription = "important",
                    modifier = Modifier
                        .width(45.dp)
                        .height(45.dp)
                        .padding(10.dp)
                        .align(Alignment.CenterVertically),
                )
            } else {
                Box(
                    modifier = Modifier
                        .width(45.dp)
                        .height(45.dp)
                        .padding(10.dp)
                ) {
                }
            }
        }
    }
}