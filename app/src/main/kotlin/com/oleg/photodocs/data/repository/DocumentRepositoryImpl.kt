package com.oleg.photodocs.data.repository

import com.oleg.photodocs.data.datasource.DocumentCacheDataSource
import com.oleg.photodocs.data.datasource.DocumentRemoteDataSource
import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.data.repository.resouces.ResourceState
import com.oleg.photodocs.datasource.model.DocumentEntity
import com.oleg.photodocs.datasource.model.mapToDomain
import com.oleg.photodocs.domain.model.DocumentModel
import com.oleg.photodocs.domain.repository.DocumentRepository

class DocumentRepositoryImpl constructor(
    private val cacheDataSource: DocumentCacheDataSource,
    private val remoteDataSource: DocumentRemoteDataSource
) : DocumentRepository {

    override suspend fun get(): Resource<List<DocumentModel>>? {
        var documentList: List<DocumentEntity>?
        var response = cacheDataSource.get()
        response.await().let {
            documentList = it
        }
        documentList?.let {
            if (!documentList?.isEmpty()!!) {
                return Resource(ResourceState.SUCCESS, documentList?.mapToDomain())
            }
        }

        val documents = remoteDataSource.get()
        cacheDataSource.set(documents = documents?.data!!)
        return documents.mapToDomain()
    }

}


