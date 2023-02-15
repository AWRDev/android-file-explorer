package com.awrdev.explorertest.presentation.file_list

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.nio.file.Files
import java.nio.file.NotDirectoryException
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.streams.toList

@RequiresApi(Build.VERSION_CODES.O)
class FileListViewModel : ViewModel() {

    private val _state = mutableStateOf(FileListState())
    val state: State<FileListState> = _state

    private var pathList: List<Path>? = null

    init {
        pathList = Files.list(Paths.get(state.value.currentDirectory)).toList()
        //directoryList = ArrayList(pathList!!.map { it.toString() }).sorted()
        getFilesList()
    }
    private fun getFilesList(){
        _state.value = FileListState(
            state.value.currentDirectory,
            null,
            ArrayList(pathList!!.map { it.toString() }).sorted())
    }

    fun updateFileList(workingDirectory: String){
        Log.d("MyTag", "Now at: $workingDirectory")
//        pathList = Files.list(Paths.get(workingDirectory)).toList()
//        _state.value = FileListState(
//            workingDirectory,
//            state.value.currentDirectory,
//            ArrayList(pathList!!.map { it.toString() }).sorted())
        try {
            pathList = Files.list(Paths.get(workingDirectory)).toList()
            _state.value = FileListState(
                workingDirectory,
                state.value.currentDirectory,
                ArrayList(pathList!!.map { it.toString() }).sorted())
        } catch (e: NotDirectoryException){
            Log.e("FileListViewModel", "Something happened")
        }
    }

    fun goUp() {
        //TODO: 2 -> maybe should pass whole list to getParentDirectory() ?
        val currentDirectoryAsList = state.value.currentDirectory.split('/')
        val directoryAbove: String = getParentDirectory(currentDirectoryAsList.last())

        //Log.d("MyTag", "current directory is" + state.value.currentDirectory)
        //Log.d("MyTag", "directory above is $directoryAbove")
        try {
            updateFileList(directoryAbove)
        } catch (e: java.nio.file.AccessDeniedException){
            Log.d("MyTag", "Didn't make it. still in ${state.value.currentDirectory}")
        }
    }

    fun goBack(){
        if (state.value.previousDirectory != null){
            updateFileList(state.value.previousDirectory!!)
        }
    }
    fun copy() {
        _state.value = FileListState(
            state.value.currentDirectory,
            state.value.previousDirectory,
            state.value.directoryList,
            true
        )
    }

    private fun getParentDirectory(lastElementsOfPathString: String): String {

        val currentDirectoryAsList = state.value.currentDirectory.split('/')
        val directoryAbove: String?
        //TODO: Optimize path selection
        //TODO: think about suggestion o n if
        if (lastElementsOfPathString == "") {
            //Log.d("MyTag", "IF FIRED")
            directoryAbove = currentDirectoryAsList.subList(0, currentDirectoryAsList.size-2)
                .joinToString(separator = "/")
        }
        else{
            //Log.d("MyTag", "ELSE FIRED")
            directoryAbove = currentDirectoryAsList.subList(0, currentDirectoryAsList.lastIndex)
                .joinToString(separator = "/")
        }
        return directoryAbove
    }

    fun cancelAction() {
        _state.value = FileListState(
            state.value.currentDirectory,
            state.value.previousDirectory,
            state.value.directoryList,
            false
        )
    }
}