package com.d42gmail.cavar.beforeandafter.custom_view

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ClipDrawable
import android.os.AsyncTask
import android.os.Looper
import android.view.Gravity
import android.widget.ImageView
import android.widget.SeekBar
import com.bumptech.glide.Glide
import java.lang.ref.WeakReference

class ClipDrawableAsync<T>(imageLeftView: ImageView, imageRightView: ImageView, seekBar: SeekBar, var progress: Int, private val loadedFinishedListener: LoadingListener? = null) : AsyncTask<T, Void, ArrayList<ClipDrawable?>>() {
    private val imageRefLeft: WeakReference<ImageView> = WeakReference(imageLeftView)
    private val imageRefRight: WeakReference<ImageView> = WeakReference(imageRightView)
    private val seekBarRef: WeakReference<SeekBar> = WeakReference(seekBar)

    override fun doInBackground(vararg args: T): ArrayList<ClipDrawable?> {
        val array = ArrayList<ClipDrawable?>()
        Looper.myLooper()?.let { Looper.prepare() }
        try {
            //Left bitmap
            var rawLeftBitmap = Glide.with(imageRefLeft.get()!!.context)
                    .load(args[0])
                    .asBitmap()
                    .centerCrop()
                    .into(getWidth(), getHeigh())
                    .get()
            val scaledLeftBitmap = getScaledBitmap(rawLeftBitmap)
            scaledLeftBitmap?.let {
                rawLeftBitmap = scaledLeftBitmap
            }
            val bitmapDrawable = BitmapDrawable(imageRefLeft.get()!!.context.resources, rawLeftBitmap)
            //Right Bitmap
            val rightBitmap = Glide.with(imageRefRight.get()!!.context)
                    .load(args[1])
                    .asBitmap()
                    .centerCrop()
                    .into(getWidth(), getHeigh())
                    .get()

            array.add(ClipDrawable(bitmapDrawable, Gravity.START, ClipDrawable.HORIZONTAL))
            array.add(ClipDrawable(BitmapDrawable(imageRefLeft.get()!!.context.resources, rightBitmap), Gravity.START, ClipDrawable.HORIZONTAL))
        } catch (e: Exception) {
            e.printStackTrace()
            array.clear()
        }
        return array
    }

    override fun onPostExecute(array: ArrayList<ClipDrawable?>) {
        if (array.size == 2 && imageRefLeft.get() != null && imageRefRight.get() != null) {
            if (array[1] != null) {
                imageRefRight.get()?.setImageDrawable(array[1]!!)
                array[1]!!.level = 10000
            }
            if (array[0] != null) {
                initSeekBar(array[0]!!)
                imageRefLeft.get()?.setImageDrawable(array[0]!!)
                val progressNum = progress
                array[0]!!.level = progressNum
            }
            loadedFinishedListener?.loadingStatus(true)
        } else {
            loadedFinishedListener?.loadingStatus(false)
        }
    }

    private fun getWidth(): Int {
        var width = 0
        while (width == 0) {
            width = imageRefLeft.get()!!.width
        }
        return width
    }

    private fun getHeigh(): Int {
        var height = 0
        while (height == 0) {
            height = imageRefLeft.get()!!.height
        }
        return height
    }

    private fun getScaledBitmap(bitmap: Bitmap): Bitmap? {
        try {
            if (imageRefLeft.get() == null)
                return bitmap
            val imageWidth = imageRefLeft.get()!!.width
            val imageHeight = imageRefLeft.get()!!.height
            if (imageWidth > 0 && imageHeight > 0)
                return Bitmap.createScaledBitmap(bitmap, imageWidth, imageHeight, false)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun initSeekBar(clipDrawable: ClipDrawable) {
        seekBarRef.get()?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                clipDrawable.level = i
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })
    }

    interface LoadingListener {
        fun loadingStatus(loadedSuccess: Boolean)
    }
}