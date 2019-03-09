package com.oleg.photodocs.domain.repository

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.DocumentModel

interface DocumentRepository {
    suspend fun get(refresh: Boolean): Resource<List<DocumentModel>>?
}
