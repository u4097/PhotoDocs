package com.oleg.photodocs.data.repository

import com.oleg.photodocs.data.datasource.DocumentCacheDataSource
import com.oleg.photodocs.data.datasource.DocumentRemoteDataSource
import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.data.repository.resouces.ResourceState
import com.oleg.photodocs.datasource.model.DocumentEntity
import com.oleg.photodocs.datasource.model.mapToDomain
import com.oleg.photodocs.domain.model.DocumentModel
import com.oleg.photodocs.domain.repository.DocumentRepository
import timber.log.Timber
import java.lang.Exception

class DocumentRepositoryImpl constructor(
    private val cacheDataSource: DocumentCacheDataSource,
    private val remoteDataSource: DocumentRemoteDataSource
) : DocumentRepository {

    override suspend fun get(refresh: Boolean): Resource<List<DocumentModel>>? {
        when (refresh) {
            true -> {
                val documents = remoteDataSource.get()
                // Save to cache
                cacheDataSource.set(documents = documents?.data!!)
                return documents.mapToDomain()
            }
        }
        var documentList: List<DocumentEntity>?
        // Get from cache
        var response = cacheDataSource.get()
        response.await().let {
            documentList = it
        }
        documentList?.let {
            if (!documentList?.isEmpty()!!) {
                Timber.d("Get document list from cache")
                return Resource(ResourceState.SUCCESS, documentList?.mapToDomain())
            }
        }

        val documents = remoteDataSource.get()
        Timber.d("Get document from server:\n ${documents?.data}")
        documents?.data?.let {
            try {
                // Save to cache
                cacheDataSource.set(documents = documents.data)
            } catch (e: Exception) {
                Timber.e("Can't save document into cache: ${e.stackTrace}")
            }
        }
        return documents?.mapToDomain()
    }

}


