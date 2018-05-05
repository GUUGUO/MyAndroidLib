package com.guuguo.android.lib.extension

import android.content.res.Resources
import android.graphics.Color
import com.guuguo.android.lib.BaseApplication
import com.guuguo.android.lib.utils.FileUtil

/**
 * Created by 大哥哥 on 2016/10/21 0021.
 */


fun Int.pxToDp(): Int {
    return (this / Resources.getSystem().displayMetrics.density + 0.5f).toInt()
}

fun Int.dpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
}

fun Int?.safe(): Int {
    if (this == null)
        return 0
    else return this
}
fun Boolean?.safe(): Boolean {
    if (this == null)
        return false
    else return this
}
fun Long.getFitSize(byte2FitMemorySize: Int = 1): String = FileUtil.byte2FitMemorySize(this, byte2FitMemorySize)
//color
/**
 * 修改颜色透明度
 * @param color
 * *
 * @param alpha
 * *
 * @return
 */
fun Int.changeAlpha(alpha: Int): Int {
    val red = Color.red(this)
    val green = Color.green(this)
    val blue = Color.blue(this)

    return Color.argb(alpha, red, green, blue)
}

/**
 * 获取格式化 保留[decimalLength] 位数的小数点，[intOptimization]true 代表整数不显示小数点
 /
fun Number.formatDecimal(decimalLength: Int = 1, intOptimization: Boolean = true): String {
    if (intOptimization && this.toFloat() - this.toInt() == 0f)
        return this.toInt().toString()
    return String.format("%.${decimalLength}f", this.toDouble())
}