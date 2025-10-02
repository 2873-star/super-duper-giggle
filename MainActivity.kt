package com.example.autoclicker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.opencv.android.OpenCVLoader

class MainActivity : AppCompatActivity() {

    private val SCREENSHOT_REQUEST_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!OpenCVLoader.initDebug()) {
            // OpenCV 초기화 실패 처리
        }

        val startButton = findViewById<Button>(R.id.startButton)
        val stopButton = findViewById<Button>(R.id.stopButton)

        startButton.setOnClickListener {
            // MediaProjection 권한 요청
            val intent = ScreenshotService.getIntent(this)
            startActivityForResult(intent, SCREENSHOT_REQUEST_CODE)
        }

        stopButton.setOnClickListener {
            val stopIntent = Intent(this, ScreenshotService::class.java)
            stopService(stopIntent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SCREENSHOT_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val serviceIntent = Intent(this, ScreenshotService::class.java)
            serviceIntent.putExtra("data", data)
            serviceIntent.putExtra("resultCode", resultCode)
            startService(serviceIntent)
        }
    }
}
