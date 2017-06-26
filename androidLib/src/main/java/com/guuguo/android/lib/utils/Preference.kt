package com.guuguo.android.util

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by 大哥哥 on 2016/10/15 0015.
 */
class Preference<T>(val context: Context, val default: T) : ReadWriteProperty<Any?, T> {
    val prefs by lazy {
        context.getSharedPreferences("order", Context.MODE_PRIVATE)
    }
    var mValue: T? = null
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (mValue == null)
            mValue = findPreference(property.name, default)
        return mValue!!
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (mValue != value)
            putPreference(property.name, value)
        mValue=value
    }

    private fun <U> findPreference(name: String, default: U): U = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
        res as U
    }

    private fun <U> putPreference(name: String, value: U) = with(prefs.edit())
    {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type c an be saved into Preferences")
        }.apply()
    }
}