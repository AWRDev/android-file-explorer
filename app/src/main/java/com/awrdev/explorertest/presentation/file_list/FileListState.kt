package com.awrdev.explorertest.presentation.file_list

import com.awrdev.explorertest.presentation.file_list.components.ExplorerAction

data class FileListState(
    val currentDirectory: String = "/storage/emulated/0/DCIM",
    val previousDirectory: String? = null,
    val directoryList: List<String> = emptyList(),
    val explorerAction: ExplorerAction = ExplorerAction.View
)