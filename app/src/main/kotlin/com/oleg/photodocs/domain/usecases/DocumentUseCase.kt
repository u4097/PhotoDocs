package com.oleg.photodocs.domain.usecases

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.DocumentModel
import com.oleg.photodocs.domain.model.login.LoginModel
import com.oleg.photodocs.domain.repository.DocumentRepository
import com.oleg.photodocs.domain.repository.LoginRepository
import com.oleg.photodocs.presentation.LoginResponse


class DocumentUseCase constructor(private val documentRepository: DocumentRepository) {

    suspend fun get(): Resource<List<DocumentModel>>? =
        documentRepository.get()

}
