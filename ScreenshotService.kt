package com.example.autoclicker

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.*
import org.opencv.core.Mat

class ScreenshotService : Service() {

    private val scope = CoroutineScope(Dispatchers.Default)
    private var job: Job? = null

    private val sct = Screenshot()
    private val mtm = MultiTemplateMatch()
    private val assets = Assets()

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, ScreenshotService::class.java)
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(1, createNotification())

        val data = intent?.getParcelableExtra<Intent>("data")
        val resultCode = intent?.getIntExtra("resultCode", Activity.RESULT_CANCELED) ?: Activity.RESULT_CANCELED

        sct.initMediaProjection(this, resultCode, data)

        job = scope.launch {
            while (isActive) {
                val currentSct: Mat = sct.getScreenshot()
                val hits = async { mtm.predict(assets.getStairAssets(), currentSct) }
                hits.await()

                delay(100) // 0.1초
            }
        }

        return START_STICKY
    }

    override fun onDestroy() {
        job?.cancel()
        sct.stopProjection()
        super.onDestroy()
    }

    private fun createNotification(): Notification {
        val channelId = "autoclicker_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "AutoClicker", NotificationManager.IMPORTANCE_LOW)
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("AutoClicker 실행 중")
            .setContentText("백그라운드에서 동작 중")
            .setSmallIcon(android.R.drawable.ic_media_play)
            .build()
    }
}
