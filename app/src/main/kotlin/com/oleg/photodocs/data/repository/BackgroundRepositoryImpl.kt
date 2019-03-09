package com.oleg.photodocs.data.repository

import com.oleg.photodocs.data.datasource.BackgroundCacheDataSource
import com.oleg.photodocs.data.datasource.BackgroundRemoteDataSource
import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.data.repository.resouces.ResourceState
import com.oleg.photodocs.datasource.model.BackgroundEntity
import com.oleg.photodocs.datasource.model.mapToDomain
import com.oleg.photodocs.domain.model.BackgroundModel
import com.oleg.photodocs.domain.repository.BackgroundRepository
import timber.log.Timber
import java.lang.Exception

class BackgroundRepositoryImpl constructor(
    private val cacheDataSource: BackgroundCacheDataSource,
    private val remoteDataSource: BackgroundRemoteDataSource
) : BackgroundRepository {

    override suspend fun get(refresh: Boolean): Resource<List<BackgroundModel>>? {
        when (refresh) {
            true -> {
                val backgrounds = remoteDataSource.get()
                // Save to cache
                cacheDataSource.set(backgrounds = backgrounds?.data!!)
                return backgrounds.mapToDomain()
            }
        }
        var backgroundList: List<BackgroundEntity>?
        // Get from cache
        var response = cacheDataSource.get()
        response.await().let {
            backgroundList = it
        }
        backgroundList?.let {
            if (!backgroundList?.isEmpty()!!) {
                Timber.d("Get background list from cache.")
                return Resource(ResourceState.SUCCESS, backgroundList?.mapToDomain())
            }
        }

        val backgrounds = remoteDataSource.get()
        Timber.d("Get background from server:\n${backgrounds?.data}")
        backgrounds?.data?.let {
            try {
                // Save to cache
                cacheDataSource.set(backgrounds = backgrounds.data)
            } catch (e: Exception) {
                Timber.e("Can't save background into cache: ${e.stackTrace}")
            }
        }
        return backgrounds?.mapToDomain()
    }

}


