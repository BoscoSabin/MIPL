package com.example.msiltask.support.imageLoader

import android.content.Context
import android.os.Environment
import java.io.File

class FileCache(context: Context) {
    private var cacheDir: File = context.cacheDir

    init {
        if (!cacheDir.exists()) {
            cacheDir.mkdirs()
        }
    }

    fun getFile(url: String): File {
        //Identify images by hashcode or encode by URLEncoder.encode.
        val filename = url.hashCode().toString()
        return File(cacheDir, filename)
    }

    fun clear() {
        // list all files inside cache directory
        val files: Array<File> = cacheDir.listFiles() ?: return
        //delete all cache directory files
        for (f in files) f.delete()
    }
}