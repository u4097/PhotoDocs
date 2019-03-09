package com.oleg.photodocs.datasource.cache

import com.oleg.photodocs.cache.AppCache
import com.oleg.photodocs.data.datasource.BackgroundCacheDataSource
import com.oleg.photodocs.datasource.model.BackgroundEntity
import com.oleg.photodocs.pref.PrefUtils.token
import kotlinx.coroutines.Deferred


class BackgroundCacheDataSourceImpl constructor(
    private val cache: AppCache<List<BackgroundEntity>>
) : BackgroundCacheDataSource {
    val key = "background"

    override fun set(backgrounds: List<BackgroundEntity>): Deferred<List<BackgroundEntity>> {
        return cache.save(key, backgrounds)
    }

    override fun get(): Deferred<List<BackgroundEntity>> =
        cache.load(key)
}
