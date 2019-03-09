package com.oleg.photodocs.data.datasource

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.datasource.model.BackgroundEntity
import kotlinx.coroutines.Deferred


interface BackgroundCacheDataSource {

    fun get(): Deferred<List<BackgroundEntity>>

    fun set(backgrounds: List<BackgroundEntity>): Deferred<List<BackgroundEntity>>

}

interface BackgroundRemoteDataSource {

    suspend fun get(): Resource<List<BackgroundEntity>>?

}
