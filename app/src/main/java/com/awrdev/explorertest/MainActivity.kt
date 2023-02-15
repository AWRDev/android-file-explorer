package com.awrdev.explorertest

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.awrdev.explorertest.presentation.file_list.FileListScreen
import com.awrdev.explorertest.presentation.file_list.FileListViewModel
import com.awrdev.explorertest.presentation.file_list.components.FileCard
import com.awrdev.explorertest.ui.theme.ExplorerTestTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(FileListViewModel::class.java)
        setContent {
            ExplorerTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FileListScreen(files = viewModel.state.value.directoryList, viewModel)
                }
            }
        }
    }
}
