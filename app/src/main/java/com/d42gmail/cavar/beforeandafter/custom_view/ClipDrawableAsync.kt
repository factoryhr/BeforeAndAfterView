package com.d42gmail.cavar.beforeandafter.custom_view

import android.content.Context
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

class ClipDrawableAsync<T>(imageAfterView: ImageView, imageBeforeView: ImageView, seekBar: SeekBar, var progress: Int, private val loadedFinishedListener: OnAfterImageLoaded? = null) : AsyncTask<T, Void, ArrayList<ClipDrawable?>>() {
    private val imageRefAfter: WeakReference<ImageView> = WeakReference(imageAfterView)
    private val imageRefBefore: WeakReference<ImageView> = WeakReference(imageBeforeView)
    private val seekBarRef: WeakReference<SeekBar> = WeakReference(seekBar)

    override fun doInBackground(vararg args: T): ArrayList<ClipDrawable?> {
        val array = ArrayList<ClipDrawable?>()
        Looper.myLooper()?.let { Looper.prepare() }
        try {
            //After bitmap
            var rawAfterBitmap = Glide.with(imageRefAfter.get()!!.context)
                    .load(args[0])
                    .asBitmap()
                    .centerCrop()
                    .into(getWidth(), getHeigh())
                    .get()
            val scaledAfterBitmap = getScaledBitmap(rawAfterBitmap)
            scaledAfterBitmap?.let {
                rawAfterBitmap = scaledAfterBitmap
            }
            val bitmapDrawable = BitmapDrawable(imageRefAfter.get()!!.context.resources, rawAfterBitmap)
            //Before Bitmap
            val beforeBitmap = Glide.with(imageRefAfter.get()!!.context)
                    .load(args[1])
                    .asBitmap()
                    .centerCrop()
                    .into(getWidth(), getHeigh())
                    .get()

            array.add(ClipDrawable(bitmapDrawable, Gravity.START, ClipDrawable.HORIZONTAL))
            array.add(ClipDrawable(BitmapDrawable(imageRefAfter.get()!!.context.resources, beforeBitmap), Gravity.START, ClipDrawable.HORIZONTAL))
        } catch (e: Exception) {
            e.printStackTrace()
            array.clear()
        }
        return array
    }

    override fun onPostExecute(array: ArrayList<ClipDrawable?>) {
        if (array.size == 2 && imageRefAfter.get() != null && imageRefBefore.get() != null) {
            if (array[1] != null) {
                imageRefBefore.get()?.setImageDrawable(array[1]!!)
                array[1]!!.level = 10000
            }
            if (array[0] != null) {
                initSeekBar(array[0]!!)
                imageRefAfter.get()?.setImageDrawable(array[0]!!)
                val progressNum = progress
                array[0]!!.level = progressNum
            }
            loadedFinishedListener?.onLoadedFinished(true)
        } else {
            loadedFinishedListener?.onLoadedFinished(false)
        }
    }

    private fun getWidth(): Int {
        var width = 0
        while (width == 0) {
            width = imageRefAfter.get()!!.width
        }
        return width
    }

    private fun getHeigh(): Int {
        var height = 0
        while (height == 0) {
            height = imageRefAfter.get()!!.height
        }
        return height
    }

    private fun getScaledBitmap(bitmap: Bitmap): Bitmap? {
        try {
            if (imageRefAfter.get() == null)
                return bitmap
            val imageWidth = imageRefAfter.get()!!.width
            val imageHeight = imageRefAfter.get()!!.height
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

    interface OnAfterImageLoaded {
        fun onLoadedFinished(loadedSuccess: Boolean)
    }
}