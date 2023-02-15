package com.awrdev.explorertest.presentation.file_list

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.awrdev.explorertest.presentation.file_list.components.FileCard
import java.nio.file.Files.isDirectory
import java.nio.file.Paths

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FileListScreen(
    files: List<String>,
    viewModel: FileListViewModel
) {

    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            for (file in files){
                Log.d("TAP", file)
            }
            for (i in 0 until 5){
                Log.d("TAP", "Numeric: " + files[i])
            }
        }){
            Text(text = "Current list is:")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            if (viewModel.state.value.isActionable){
                Button(onClick = { viewModel.cancelAction() }) {
                    Text(text = "X")
                }
            }
            Button(onClick = { viewModel.goUp() }) {
                Text(text = "Go up")
            }
            Button(onClick = {viewModel.goBack()}) {
                Text(text = "Go back")
            }
        }
        Row(modifier = Modifier.padding(5.dp)) {
            Text(text = viewModel.state.value.currentDirectory)
        }
        
        if (files.isNotEmpty()){
            LazyColumn(modifier = Modifier.fillMaxHeight().weight(1f)) {
                items(files.size) {fileNum ->
                    //onTap StateSave solution: https://stackoverflow.com/questions/75004121/ontap-detecttapgestures-not-working-properly-as-clickables-with-same-code-an/75004569#75004569
                    val onTap: () -> Unit = {
                    viewModel.updateFileList(files[fileNum])
                }
                    val folderListUpdated = rememberUpdatedState(onTap)
                    FileCard(filename = files[fileNum],
                        isDirectory = isDirectory(Paths.get(files[fileNum])),
                        isSelectable = viewModel.state.value.isActionable,
                        modifier = Modifier
//                            .clickable {
//                                viewModel.updateFileList(files[fileNum])
//                            }
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onLongPress = { viewModel.copy() },
                                    onTap = {
                                        folderListUpdated.value()
                                    }
                                )
                            }
                    )
                }
            }
            if (viewModel.state.value.isActionable){
                Row(modifier = Modifier.fillMaxWidth().height(50.dp).background(Color.Red).clickable {

                }){
                    Text(text = "Here is buttons will be")
                }
            }
        }
        else{
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "The folder is empty")
            }
        }
    }
}

//@Preview
//@Composable
//fun Box(){
//    FileListScreen(files = mutableListOf("Hello", "Hello", "Hello", "Hello", "Hello", "Hello", "Hello", ))
//}
//Some test comment