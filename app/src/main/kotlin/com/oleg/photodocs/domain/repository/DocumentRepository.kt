package com.oleg.photodocs.domain.repository

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.DocumentModel
import com.oleg.photodocs.domain.model.login.LoginModel
import com.oleg.photodocs.presentation.LoginResponse

interface DocumentRepository {
    suspend fun get(): Resource<List<DocumentModel>>?
}
