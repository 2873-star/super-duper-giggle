package com.example.autoclicker

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.ImageReader
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import org.opencv.android.Utils
import org.opencv.core.CvType
import org.opencv.core.Mat

class Screenshot {

    private var mediaProjection: MediaProjection? = null
    private var imageReader: ImageReader? = null

    fun initMediaProjection(context: Context, resultCode: Int, data: Intent?) {
        val mgr = context.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        mediaProjection = mgr.getMediaProjection(resultCode, data!!)
        imageReader = ImageReader.newInstance(1080, 2400, android.graphics.PixelFormat.RGBA_8888, 2)
        mediaProjection?.createVirtualDisplay(
            "screenshot", 1080, 2400, 420,
            0, imageReader?.surface, null, null
        )
    }

    fun getScreenshot(): Mat {
        // 실제 구현: ImageReader에서 Bitmap으로 변환 후 OpenCV Mat 변환
        val bmp = Bitmap.createBitmap(1080, 2400, Bitmap.Config.ARGB_8888) // 자리 표시자
        val mat = Mat(bmp.height, bmp.width, CvType.CV_8UC4)
        Utils.bitmapToMat(bmp, mat)
        return mat
    }

    fun stopProjection() {
        mediaProjection?.stop()
    }
}
