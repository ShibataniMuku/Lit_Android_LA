package com.shibatani.mukkun.screentime

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Process
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.shibatani.mukkun.screentime.databinding.ActivityMainBinding
import android.provider.Settings
import android.icu.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var editRate: EditText
    lateinit var textRate: TextView
    lateinit var buttonRate: Button
    lateinit var checkNotClockWise: CheckBox
    lateinit var chartView: ChartView
    lateinit var relativeLayout: RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        val pref: SharedPreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)

        if (!checkReadStatsPermission()) {
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        }

        // 目標の使用時間を取得
        val goalHour = pref.getInt("GoalHour", 2)
        val goalMinis = pref.getInt("GoalMinis", 0)
        val goalAllMinis = goalHour * 60 + goalMinis

        binding.remainingTimeText.text = "残り " + goalHour + "時間" + goalMinis + "分"

        // 現在の使用時間を取得
        val usageStatsManager = UsageStatsManager(this)
        val interval = Calendar.DAY_OF_MONTH // 日単位の総使用時間を取得する場合
        val totalUsageTime = usageStatsManager.getTotalUsageTimeForInterval(interval)

//        binding.goalPercentageText.text = ((totalUsageTime.toFloat() / goalAllMinis.toFloat()) * 100).toString()

        //表示用サンプルデータの作成//
        val dimensions = listOf("使用時間", "残り時間")//分割円の名称(String型)
        val values = listOf(totalUsageTime.toFloat(), goalAllMinis.toFloat())//分割円の大きさ(Float型)
        //①Entryにデータ格納
        var entryList = mutableListOf<PieEntry>()
        for(i in values.indices){
            entryList.add(
                PieEntry(values[i], dimensions[i])
            )
        }
        //②PieDataSetにデータ格納
        val pieDataSet = PieDataSet(entryList, "candle")
        //③DataSetのフォーマット指定
        pieDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()
        //④PieDataにPieDataSet格納
        val pieData = PieData(pieDataSet)
        //⑤PieChartにPieData格納
        var pieChart: PieChart = findViewById(R.id.pie_chart)
        pieChart.data = pieData
        //⑥Chartのフォーマット指定
        pieChart.legend.isEnabled = false
        //⑦PieChart更新
        pieChart.invalidate()

//        val context = applicationContext
//        relativeLayout = RelativeLayout(context)
//        setContentView(relativeLayout)
//
//        editRate = EditText(context)
//        editRate.setText("75") //とりあえずデフォルトは75%とする
//        editRate.setInputType(InputType.TYPE_CLASS_NUMBER)
//
//        textRate = TextView(context)
//        textRate.gravity = Gravity.CENTER
//        textRate.setText("%")
//
//        buttonRate = Button(context)
//        buttonRate.setText("グラフ表示")
//        buttonRate.setOnClickListener {
//            drawChart()
//        }
//
//        checkNotClockWise = CheckBox(context)
//        checkNotClockWise.setText("反時計回りにする")
//
//        chartView = ChartView(context)
//
        // 今週の目標を設定するボタンが押されたら
        binding.goalSettingsButton.setOnClickListener {
            val setGoalActivity = Intent(this, GoalSettings::class.java)
            startActivity(setGoalActivity)
            finish()
        }
    }

    private fun checkReadStatsPermission(): Boolean {
        // AppOpsManagerを取得
        val aom = getSystemService(APP_OPS_SERVICE) as AppOpsManager
        // GET_USAGE_STATSのステータスを取得
        val mode = aom.checkOp(
            AppOpsManager.OPSTR_GET_USAGE_STATS, Process.myUid(),
            packageName
        )
        return if (mode == AppOpsManager.MODE_DEFAULT) {
            // AppOpsの状態がデフォルトなら通常のpermissionチェックを行う。
            // 普通のアプリならfalse
            checkPermission(
                "android.permission.PACKAGE_USAGE_STATS",
                Process.myPid(),
                Process.myUid()
            ) == PackageManager.PERMISSION_GRANTED
        } else mode == AppOpsManager.MODE_ALLOWED
        // AppOpsの状態がデフォルトでないならallowedのみtrue
    }

//
//    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        super.onWindowFocusChanged(hasFocus)
//
//        if (!hasFocus) {
//            return
//        }
//        changeScreen()
//        drawChart()
//    }
//
//    private fun changeScreen(){
//        val widthValue = relativeLayout.width
//        val heightValue = relativeLayout.height
//
//        val buttonHeight = heightValue / 10
//        val spaceWidth = (widthValue-buttonHeight*6)/2
//        relativeLayout.addView(editRate,getLayoutParams(spaceWidth,50,buttonHeight,buttonHeight))
//        relativeLayout.addView(textRate,getLayoutParams(spaceWidth+buttonHeight,50,buttonHeight,buttonHeight))
//        relativeLayout.addView(checkNotClockWise,getLayoutParams(spaceWidth+buttonHeight*2,50,buttonHeight*2,buttonHeight))
//        relativeLayout.addView(buttonRate,getLayoutParams(spaceWidth+buttonHeight*4,50,buttonHeight*2,buttonHeight))
//
//
//        var chartWidth: Int = (widthValue * 0.8).toInt()
//        if (widthValue > heightValue){
//            chartWidth = (heightValue * 0.8).toInt()
//        }
//
//        relativeLayout.addView(chartView,getLayoutParams(widthValue/2-chartWidth/2,50+buttonHeight,chartWidth,chartWidth))
//    }
//
//    private fun getLayoutParams(x: Int, y: Int, width: Int, height: Int): RelativeLayout.LayoutParams {
//        val param1 = RelativeLayout.LayoutParams(width, height)
//        param1.leftMargin = x
//        param1.topMargin = y
//        param1.addRule(RelativeLayout.ALIGN_TOP)
//        return param1
//    }
//
//    /**
//     * グラフをアニメーションで表示
//     */
//    private fun drawChart(){
//        chartView.isNotClockWise = checkNotClockWise.isChecked
//
//        val rateString:String = editRate.getText().toString()
//        val rate:Int = Integer.parseInt(rateString)
//
//        val anmationForChartView = AnimationForChartView(chartView)
//        anmationForChartView.rate = rate
//        anmationForChartView.duration = 2000
//        chartView.startAnimation(anmationForChartView)
//    }

}