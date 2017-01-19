package com.guuguo.learnsave.extension

import java.util.*

/**
 * Created by 大哥哥 on 2016/10/21 0021.
 */

fun <T> List<T>.intersection(list: List<T>): ArrayList<T> {
    var temp = ArrayList<T>()
    list.forEach { nModel ->
        var isOld = false
        for (oModel in this) {
            if (nModel!!.equals(oModel)) {
                temp.add(oModel)
                isOld = true
                break
            }
        }
        if (!isOld)
            temp.add(nModel)
    }
    return temp
}

fun <T> List<T>?.safe(): List<T> {
    if (this == null)
        return ArrayList()
    else {
        return this;
    }
}
