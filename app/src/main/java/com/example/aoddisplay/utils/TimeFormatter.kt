package com.example.aoddisplay.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object TimeFormatter {

    // 返回左上角：日期 + 周几，格式 yyyy-MM-dd EEEE
    fun getDateWeekString(timeZone: TimeZone = TimeZone.getDefault()): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd EEEE", Locale.getDefault())
        sdf.timeZone = timeZone
        return sdf.format(Date())
    }

    // 返回中央：小时:分钟，24小时制 HH:mm
    fun getHourMinuteString(timeZone: TimeZone = TimeZone.getDefault()): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        sdf.timeZone = timeZone
        return sdf.format(Date())
    }

    // 返回右下角：秒钟 ss
    fun getSecondString(timeZone: TimeZone = TimeZone.getDefault()): String {
        val sdf = SimpleDateFormat("ss", Locale.getDefault())
        sdf.timeZone = timeZone
        return sdf.format(Date())
    }
}
