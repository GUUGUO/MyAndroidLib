package com.guuguo.android.lib.utils.cache

import com.guuguo.android.lib.utils.Utils
import io.reactivex.*
import java.io.Serializable

/** 转成带缓存的网络请求,Pair<T,Boolean> Boolean代表是否是从缓存读取的的结果*/
class ACacheTransform<T : Serializable>(var key: String) : ObservableTransformer<T, Pair<T, Boolean>> {
    val FROM_NET = 0
    val FROM_CACHE = 1
    val FROM_CACHE_AND_NET = 2
    val NO_CACHE = 3
    val FROM_CACHE_IF_VALIDE = 4
    var fromType = FROM_CACHE_AND_NET

    fun fromCache() = this.also { fromType = FROM_CACHE }
    fun noCache() = this.also { fromType = NO_CACHE }
    fun fromNet() = this.also { fromType = FROM_NET }
    fun fromCacheAndNet() = this.also { fromType = FROM_CACHE_AND_NET }
    fun fromCacheIfValide() = this.also { fromType = FROM_CACHE_IF_VALIDE }

    val aCache = ACache.get(Utils.getContext())
    override fun apply(upstream: Observable<T>): Observable<Pair<T, Boolean>> {
        var canGetFromCache = false
        val getFromCacheObservable = try {
            Observable.just(aCache.getAsObject(key) as T to true).also {
                canGetFromCache = true
            }
        } catch (e: Exception) {
            Observable.never<Pair<T, Boolean>>()
        }

        return when (fromType) {
            FROM_NET -> upstream.map { aCache.put(key, it);it to false }
            FROM_CACHE -> {
                getFromCacheObservable
            }
            FROM_CACHE_IF_VALIDE -> {
                return if (canGetFromCache)
                    getFromCacheObservable
                else upstream.map { it to false }

            }
            FROM_CACHE_AND_NET -> {
                Observable.mergeDelayError(upstream.map { aCache.put(key, it);it to false }, getFromCacheObservable)
            }
            NO_CACHE -> {
                upstream.map { it to false }
            }
            else -> upstream.map { it to false }
        }
    }
}

class ACacheTransformF<T : Serializable>(var key: String) : FlowableTransformer<T, Pair<T, Boolean>> {
    val FROM_NET = 0
    val FROM_CACHE = 1
    val FROM_CACHE_AND_NET = 2
    val NO_CACHE = 3
    val FROM_CACHE_IF_VALIDE = 4
    var fromType = FROM_CACHE_AND_NET

    fun fromCache() = this.also { fromType = FROM_CACHE }
    fun noCache() = this.also { fromType = NO_CACHE }
    fun fromNet() = this.also { fromType = FROM_NET }
    fun fromCacheAndNet() = this.also { fromType = FROM_CACHE_AND_NET }
    fun fromCacheIfValide() = this.also { fromType = FROM_CACHE_IF_VALIDE }

    val aCache = ACache.get(Utils.getContext())
    override fun apply(upstream: Flowable<T>): Flowable<Pair<T, Boolean>> {

        var canGetFromCache = false
        val getFromCacheObservable = try {
            Flowable.just(aCache.getAsObject(key) as T to true).also {
                canGetFromCache = true
            }
        } catch (e: Exception) {
            Flowable.never<Pair<T, Boolean>>()
        }

        return when (fromType) {
            FROM_NET -> upstream.map { aCache.put(key, it);it to false }
            FROM_CACHE -> {
                getFromCacheObservable
            }
            FROM_CACHE_IF_VALIDE -> {
                return if (canGetFromCache)
                    getFromCacheObservable
                else upstream.map { it to false }

            }
            FROM_CACHE_AND_NET -> {
                Flowable.mergeDelayError(upstream.map { aCache.put(key, it);it to false }, getFromCacheObservable)
            }
            NO_CACHE -> {
                upstream.map { it to false }
            }
            else -> upstream.map { it to false }
        }
    }
}