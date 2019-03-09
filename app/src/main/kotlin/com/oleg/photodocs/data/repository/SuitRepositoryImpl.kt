package com.oleg.photodocs.data.repository

import com.oleg.photodocs.data.datasource.SuitCacheDataSource
import com.oleg.photodocs.data.datasource.SuitRemoteDataSource
import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.data.repository.resouces.ResourceState
import com.oleg.photodocs.datasource.model.SuitEntity
import com.oleg.photodocs.datasource.model.mapToDomain
import com.oleg.photodocs.domain.model.SuitModel
import com.oleg.photodocs.domain.repository.SuitRepository
import timber.log.Timber
import java.lang.Exception

class SuitRepositoryImpl constructor(
    private val cacheDataSource: SuitCacheDataSource,
    private val remoteDataSource: SuitRemoteDataSource
) : SuitRepository {

    override suspend fun get(refresh: Boolean): Resource<List<SuitModel>>? {
        when (refresh) {
            true -> {
                val suits = remoteDataSource.get()
                // Save to cache
                cacheDataSource.set(suits = suits?.data!!)
                return suits.mapToDomain()
            }
        }
        var suitList: List<SuitEntity>?
        // Get from cache
        var response = cacheDataSource.get()
        response.await().let {
            suitList = it
        }
        suitList?.let {
            if (!suitList?.isEmpty()!!) {
                Timber.d("Get suit list from cache.")
                return Resource(ResourceState.SUCCESS, suitList?.mapToDomain())
            }
        }

        val suits = remoteDataSource.get()
        Timber.d("Get suit from server:\n${suits?.data}")
        suits?.data?.let {
            try {
                // Save to cache
                cacheDataSource.set(suits = suits.data)
            } catch (e: Exception) {
                Timber.e("Can't save suit into cache: ${e.stackTrace}")
            }
        }
        return suits?.mapToDomain()
    }

}


