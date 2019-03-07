package com.oleg.photodocs.cache

import androidx.lifecycle.LiveData
import com.appmattus.layercache.Cache
import com.appmattus.layercache.createLruCache
import com.appmattus.layercache.toLiveData
import kotlinx.coroutines.Deferred


class Cache<T> {

    val book = Cache.createLruCache<String,Any>(1000)

    fun load(key: String): Deferred<T> = book.get(key) as Deferred<T>

    fun save(key: String, anyObject: Any) =
        book.set(key, anyObject)
}

class LiveDataCache<T> {

    val book = Cache.createLruCache<String,Any>(1000)

    val liveDataCache = book.toLiveData()

    fun load(key: String): Deferred<T> = liveDataCache.get(key) as Deferred<T>

    fun save(key: String, anyObject: T?): LiveData<T> =
        liveDataCache.set(key, anyObject!!) as LiveData<T>

}
