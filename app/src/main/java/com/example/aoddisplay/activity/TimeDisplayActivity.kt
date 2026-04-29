package com.example.aoddisplay.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.aoddisplay.databinding.ActivityTimeDisplayBinding
import com.example.aoddisplay.utils.TimeFormatter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimeDisplayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimeDisplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 初始化ViewBinding
        binding = ActivityTimeDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 启用全屏和常亮模式
        enableFullScreenAndKeepAwake()

        // 立即更新时间一次
        updateTime()

        // 在 STARTED 生命周期内每秒更新一次（lifecycle-aware）
        lifecycleScope.launchWhenStarted {
            while (true) {
                updateTime()
                delay(1000L)
            }
        }
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
