package com.xayah.materialyoufileexplorer

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import com.dylanc.activityresult.launcher.StartActivityLauncher

class MaterialYouFileExplorer {
    private lateinit var startActivityLauncher: StartActivityLauncher

    fun initialize(activity: ComponentActivity) {
        startActivityLauncher = StartActivityLauncher(activity)
    }

    fun initialize(fragment: Fragment) {
        startActivityLauncher = StartActivityLauncher(fragment)
    }

    fun toExplorer(
        context: Context,
        isFile: Boolean,
        callback: (path: String, isFile: Boolean) -> Unit
    ) {
        val intent = Intent(
            context,
            ExplorerActivity::class.java
        ).putExtra("isFile", isFile)
        startActivityLauncher.launch(intent) { resultCode, data ->
            if (resultCode == RESULT_OK) {
                callback(
                    data?.getStringExtra("path") ?: "",
                    data?.getBooleanExtra("isFile", false) ?: false
                )
            }
        }
    }
}