package com.awrdev.explorertest.presentation.file_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.awrdev.explorertest.ui.theme.Purple500
import com.awrdev.explorertest.ui.theme.Purple700

@Composable
fun BottomBarMenu(
    size: Float
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Purple700),
        horizontalArrangement = Arrangement.Center
    ){
        Button(onClick = {/*TODO: implement copying*/},
            modifier = Modifier
                //.fillMaxSize(size)
                .size(50.dp)
                .clip(RectangleShape)
        ){
            Text(text = "Copy")
        }
    }
}

@Preview
@Composable
fun BBM_Box(){
    BottomBarMenu(1f)
}