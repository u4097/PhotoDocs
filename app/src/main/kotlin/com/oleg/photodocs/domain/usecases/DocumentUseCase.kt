package com.oleg.photodocs.domain.usecases

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.DocumentModel
import com.oleg.photodocs.domain.repository.DocumentRepository


class DocumentUseCase constructor(private val documentRepository: DocumentRepository) {

    suspend fun get(refresh: Boolean): Resource<List<DocumentModel>>? =
        documentRepository.get(refresh)

}
