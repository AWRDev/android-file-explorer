package com.awrdev.explorertest.presentation.file_list

data class FileListState(
    val currentDirectory: String = "/storage/emulated/0/DCIM",
    val previousDirectory: String? = null,
    val directoryList: List<String> = emptyList(),
    val isActionable: Boolean = false
)