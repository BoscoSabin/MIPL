package com.example.msiltask.support.imageLoader

import android.graphics.Bitmap
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class MemoryCache {
    //Last argument true for LRU ordering
    private val cache = Collections.synchronizedMap(
        LinkedHashMap<String, Bitmap>(10, 1.5f, true)
    )

    //current allocated size
    private var size: Long = 0

    //max memory cache folder used to download images in bytes
    private var limit: Long = 1000000
    fun setLimit(new_limit: Long) {
        limit = new_limit
    }

    operator fun get(id: String): Bitmap? {
        return try {
            if (!cache.containsKey(id)) null else cache[id]
        } catch (ex: NullPointerException) {
            ex.printStackTrace()
            null
        }
    }

    fun put(id: String, bitmap: Bitmap) {
        try {
            if (cache.containsKey(id)) size -= getSizeInBytes(cache[id])
            cache[id] = bitmap
            size += getSizeInBytes(bitmap)
            checkSize()
        } catch (th: Throwable) {
            th.printStackTrace()
        }
    }

    private fun checkSize() {
        if (size > limit) {

            //least recently accessed item will be the first one iterated
            val iter: MutableIterator<Map.Entry<String, Bitmap>> = cache.entries.iterator()
            while (iter.hasNext()) {
                val entry = iter.next()
                size -= getSizeInBytes(entry.value)
                iter.remove()
                if (size <= limit) break
            }
        }
    }

    fun clear() {
        try {
            // Clear cache
            cache.clear()
            size = 0
        } catch (ex: NullPointerException) {
            ex.printStackTrace()
        }
    }

    fun getSizeInBytes(bitmap: Bitmap?): Long {
        return if (bitmap == null) 0 else (bitmap.rowBytes * bitmap.height).toLong()
    }

    companion object {
        private const val TAG = "MemoryCache"
        fun CopyStream(`is`: InputStream, os: OutputStream) {
            val buffer_size = 1024
            try {
                val bytes = ByteArray(buffer_size)
                while (true) {

                    //Read byte from input stream
                    val count = `is`.read(bytes, 0, buffer_size)
                    if (count == -1) break

                    //Write byte from output stream
                    os.write(bytes, 0, count)
                }
            } catch (ex: Exception) {
            }
        }
    }

    init {

        //use 25% of available heap size
        setLimit(Runtime.getRuntime().maxMemory() / 4)
    }
}