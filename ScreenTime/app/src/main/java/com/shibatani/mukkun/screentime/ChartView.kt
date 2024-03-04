package com.shibatani.mukkun.screentime

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View

class ChartView: View {
    var rate:Float = 0f // 0 - 100の間で指定
    var isNotClockWise:Boolean = false //false 時計回り true 反時計回り

    /**
     * コンストラクタ
     */
    constructor(context: Context) : super(context, null)

    /**
     * 描画処理
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBaseChart(canvas)
        drawValueChart(canvas)
    }

    /**
     * 円グラフの軸となる円の表示
     */
    private fun drawBaseChart(canvas:Canvas?){
        val paint = Paint()
        paint.setColor(Color.rgb(255,212,121))
        val storkeWidth:Float = (getWidth() / 8).toFloat()
        paint.setStrokeWidth(storkeWidth)
        paint.setAntiAlias(true)
        paint.setStyle(Paint.Style.STROKE)
        paint.strokeCap = Paint.Cap.ROUND
        val rect = RectF(storkeWidth/2, storkeWidth/2, getWidth().toFloat()-storkeWidth/2, getHeight().toFloat()-storkeWidth/2)
        canvas!!.drawArc(rect, 0f, 360f, false, paint)
    }

    /**
     * 円グラフの値を示す円(半円)の表示
     */
    private fun drawValueChart(canvas:Canvas?){
        val paint = Paint()
        paint.setColor(Color.rgb(255,138,216))
        val storkeWidth:Float = (getWidth() / 8).toFloat()
        paint.setStrokeWidth(storkeWidth)
        paint.setAntiAlias(true)
        paint.setStyle(Paint.Style.STROKE)
        paint.strokeCap = Paint.Cap.ROUND
        val rect = RectF(storkeWidth/2, storkeWidth/2, getWidth().toFloat()-storkeWidth/2, getHeight().toFloat()-storkeWidth/2)
        var angle = rate / 100 * 360 //円グラフの終了位置の指定
        if (isNotClockWise) {
            //反時計周りの場合はマイナスにする
            angle *= -1
        }
        canvas!!.drawArc(rect, -90f, angle, false, paint)
    }
}