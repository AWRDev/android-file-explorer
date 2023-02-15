package com.awrdev.explorertest.presentation.file_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.awrdev.explorertest.R

@Composable
fun FileCard(filename: String,
                isSelectable: Boolean,
                isDirectory: Boolean,
                modifier: Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RectangleShape
            ),
        verticalAlignment = Alignment.CenterVertically){

        Image(
            if (isDirectory) painterResource(id = R.drawable.baseline_folder_24)
            else painterResource(id = R.drawable.baseline_file_24),
            contentDescription = "File icon",
            modifier = Modifier
                .size(50.dp)
                .padding(5.dp))
        Text(text = filename.split('/').last(), modifier = Modifier
            .fillMaxWidth()
            .weight(1f))
        if (isSelectable){
            Checkbox(checked = false, onCheckedChange = {}, Modifier.padding(end = 5.dp))
        }
    }
}

@Preview
@Composable
fun Box(){
    FileCard(filename = "Hello", isDirectory = false, isSelectable = true, modifier = Modifier)
}