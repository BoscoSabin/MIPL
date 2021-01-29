package com.example.msiltask.support.imageLoader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.example.msiltask.R
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ImageLoader(context: Context?) {
    var memoryCache = MemoryCache()
    var fileCache: FileCache

    //Create Map (collection) to store image and image url in key value pair
    private val imageViews = Collections.synchronizedMap(
        WeakHashMap<ImageView, String>()
    )
    var executorService: ExecutorService

    //handler to display images in UI thread
//    var handler = Handler()

    // default image show in list (Before online image download)
    val stubId = R.drawable.dafult_placeholder
    fun DisplayImage(url: String, imageView: ImageView) {
        //Store image and url in Map
        imageViews[imageView] = url

        //Check image is stored in MemoryCache Map or not (see MemoryCache.java)
        val bitmap = memoryCache[url]
        if (bitmap != null) {
            // if image is stored in MemoryCache Map then
            // Show image in listview row
            imageView.setImageBitmap(bitmap)
        } else {
            //queue Photo to download from url
            queuePhoto(url, imageView)

            //Before downloading image show default image
            imageView.setImageResource(stubId)
        }
    }

    private fun queuePhoto(url: String, imageView: ImageView) {
        // Store image and url in PhotoToLoad object
        val p = PhotoToLoad(url, imageView)

        // pass PhotoToLoad object to PhotosLoader runnable class
        // and submit PhotosLoader runnable to executers to run runnable
        // Submits a PhotosLoader runnable task for execution
        executorService.submit(PhotosLoader(p))
    }

    //Task for the queue
    inner class PhotoToLoad(var url: String, var imageView: ImageView)
    internal inner class PhotosLoader(var photoToLoad: PhotoToLoad) : Runnable {
        override fun run() {
            try {
                //Check if image already downloaded
                if (imageViewReused(photoToLoad)) return
                // download image from web url
                getBitmap(photoToLoad.url)?.also {
                    memoryCache.put(photoToLoad.url, it)
                    if (imageViewReused(photoToLoad)) return

                    // Get bitmap to display
                    val bd = BitmapDisplayer(it, photoToLoad)

                    // Causes the Runnable bd (BitmapDisplayer) to be added to the message queue.
                    // The runnable will be run on the thread to which this handler is attached.
                    // BitmapDisplayer run method will call
                    Handler(Looper.getMainLooper()).post(bd)
                }

                // set image data in Memory Cache

            } catch (th: Throwable) {
                th.printStackTrace()
            }
        }
    }

    private fun getBitmap(url: String): Bitmap? {
        val f = fileCache.getFile(url)

        //from SD cache
        //CHECK : if trying to decode file which not exist in cache return null
        val b = decodeFile(f)
        return b
            ?: try {
                var bitmap: Bitmap?
                val imageUrl = URL(url)
                val conn = imageUrl.openConnection() as HttpURLConnection
                conn.connectTimeout = 30000
                conn.readTimeout = 30000
                conn.instanceFollowRedirects = true
                val `is` = conn.inputStream

                // Constructs a new FileOutputStream that writes to file
                // if file not exist then it will create file
                val os: OutputStream = FileOutputStream(f)

                // See Utils class CopyStream method
                // It will each pixel from input stream and
                // write pixels to output stream (file)
                MemoryCache.CopyStream(`is`, os)
                os.close()
                conn.disconnect()

                //Now file created and going to resize file with defined height
                // Decodes image and scales it to reduce memory consumption
                bitmap = decodeFile(f)
                bitmap
            } catch (ex: Throwable) {
                ex.printStackTrace()
                if (ex is OutOfMemoryError) memoryCache.clear()
                null
            }

        // Download image file from web
    }

    //Decodes image and scales it to reduce memory consumption
    private fun decodeFile(f: File): Bitmap? {
        try {

            //Decode image size
            val o = BitmapFactory.Options()
            o.inJustDecodeBounds = true
            val stream1 = FileInputStream(f)
            BitmapFactory.decodeStream(stream1, null, o)
            stream1.close()

            //Find the correct scale value. It should be the power of 2.

            // Set width/height of recreated image
            val REQUIRED_SIZE = 85
            var width_tmp = o.outWidth
            var height_tmp = o.outHeight
            var scale = 1
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) break
                width_tmp /= 2
                height_tmp /= 2
                scale *= 2
            }

            //decode with current scale values
            val o2 = BitmapFactory.Options()
            o2.inSampleSize = scale
            val stream2 = FileInputStream(f)
            val bitmap = BitmapFactory.decodeStream(stream2, null, o2)
            stream2.close()
            return bitmap
        } catch (e: FileNotFoundException) {
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun imageViewReused(photoToLoad: PhotoToLoad): Boolean {
        val tag = imageViews[photoToLoad.imageView]
        //Check url is already exist in imageViews MAP
        return if (tag == null || tag != photoToLoad.url) true else false
    }

    //Used to display bitmap in the UI thread
    internal inner class BitmapDisplayer(var bitmap: Bitmap?, var photoToLoad: PhotoToLoad) :
        Runnable {
        override fun run() {
            if (imageViewReused(photoToLoad)) return

            // Show bitmap on UI
            if (bitmap != null) photoToLoad.imageView.setImageBitmap(bitmap) else photoToLoad.imageView.setImageResource(
                stubId
            )
        }
    }

    fun clearCache() {
        //Clear cache directory downloaded images and stored data in maps
        memoryCache.clear()
        fileCache.clear()
    }

    init {
        fileCache = FileCache(context!!)

        // Creates a thread pool that reuses a fixed number of
        // threads operating off a shared unbounded queue.
        executorService = Executors.newFixedThreadPool(5)
    }
}