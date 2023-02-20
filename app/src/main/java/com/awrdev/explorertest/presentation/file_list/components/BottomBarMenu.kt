package com.awrdev.explorertest.presentation.file_list.components

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.awrdev.explorertest.R
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
        Column(modifier = Modifier
            .background(Purple500)
            .clickable { },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
            Image(
                painterResource(id = R.drawable.outline_file_copy_24),
                contentDescription = "Copy icon",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(28.dp))
            Text(text = "Copy",
                fontSize = 14.sp,
                color = Color.White)
        }
    }
}

@Preview
@Composable
fun BBM_Box(){
    BottomBarMenu(1f)
}