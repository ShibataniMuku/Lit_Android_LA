package com.shibatani.mukkun.screentime

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.Date
import java.util.Locale

class UsageStatsManager(private val context: Context) : AppCompatActivity() {

    fun getUsageStatistics(startTime: Long, endTime: Long): List<UsageStats>? {
        val usageStatsManager =
            context.getSystemService(Context.USAGE_STATS_SERVICE) as? UsageStatsManager

        return usageStatsManager?.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            startTime,
            endTime
        )
    }

    fun getUsageStatisticsForInterval(interval: Int): List<UsageStats>? {
        val calendar = Calendar.getInstance()
        val endTime = calendar.timeInMillis
        calendar.add(interval, -1) // 前日 or 前週 or 前月 or 前年
        val startTime = calendar.timeInMillis

        return getUsageStatistics(startTime, endTime)
    }

    fun getTotalUsageTimeForInterval(interval: Int): Long {
        val stats = getUsageStatisticsForInterval(interval)
        var totalUsageTime = 0L
        stats?.forEach { stat ->
            totalUsageTime += stat.totalTimeInForeground
        }
        return totalUsageTime
    }

    fun printUsageStatistics(interval: Int) {
        val stats = getUsageStatisticsForInterval(interval)
        stats?.forEach { stat ->
            Log.d(
                "UsageStats",
                "Package: ${stat.packageName}, Time used: ${stat.totalTimeInForeground}ms"
            )
        }
    }

    private fun getStringDate(milliseconds: Long): String {
        val df: DateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPANESE)
        val date = Date(milliseconds)
        return df.format(date)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        val context: Context = applicationContext
    }
}

//lateinit var context: Context

//fun UsageStatsManager(mContext: Context) {
//    // contextを代入
//    context = mContext
//}

//fun main() {
////    val context: Context // ここに適切なコンテキストを設定
//    val usageStatsManager = UsageStatsManager(context)
//
//    // 1日前から今日までの使用時間を取得
//    val totalUsageTime = usageStatsManager.getTotalUsageTimeForInterval(Calendar.DAY_OF_MONTH)
//    Log.d("TotalUsageTime", "Total usage time for the past day: $totalUsageTime ms")
//
//    // アプリごとの使用時間をログに出力
//    usageStatsManager.printUsageStatistics(Calendar.DAY_OF_MONTH)
//}