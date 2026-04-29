package com.example.aoddisplay.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.aoddisplay.databinding.ActivityTimeDisplayBinding
import com.example.aoddisplay.utils.TimeFormatter

class TimeDisplayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimeDisplayBinding
    private val handler = Handler(Looper.getMainLooper())
    private val updateTimeRunnable = object : Runnable {
        override fun run() {
            updateTime()
            handler.postDelayed(this, 1000) // 每秒更新一次
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 初始化ViewBinding
        binding = ActivityTimeDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 启用全屏和常亮模式
        enableFullScreenAndKeepAwake()
        
        // 开始更新时间
        updateTime()
        handler.post(updateTimeRunnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(updateTimeRunnable)
    }

    private fun enableFullScreenAndKeepAwake() {
        // 屏幕常亮
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        
        // 全屏显示
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        
        // 隐藏导航栏和状态栏
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (
            android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
                or android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            )
    }

    private fun updateTime() {
        // 更新左上角的日期和周几
        binding.dateWeekText.text = TimeFormatter.getDateWeekString()
        
        // 更新中央的小时和分钟
        binding.timeText.text = TimeFormatter.getHourMinuteString()
        
        // 更新右下角的秒钟
        binding.secondText.text = TimeFormatter.getSecondString()
    }
}
