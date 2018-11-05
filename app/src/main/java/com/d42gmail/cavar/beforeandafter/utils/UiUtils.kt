@file:JvmName("UiUtils")
package com.d42gmail.cavar.beforeandafter.utils

import android.content.Context
import android.util.DisplayMetrics
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

fun convertDpToPix(dp: Int, context: Context): Int {
    val metric = context.resources.displayMetrics
    return (dp * (metric.densityDpi / DisplayMetrics.DENSITY_DEFAULT))
}
