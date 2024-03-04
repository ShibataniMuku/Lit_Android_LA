package com.shibatani.mukkun.screentime

import android.view.animation.Animation
import android.view.animation.Transformation

class AnimationForChartView: Animation {
    lateinit var chartView:ChartView

    var rate:Int = 0 // 0 - 100の間で指定

    constructor(chartView: ChartView){
        this.chartView = chartView
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)

        val thisRate:Float = rate * interpolatedTime
        chartView.rate = thisRate
        chartView.requestLayout()
    }
}