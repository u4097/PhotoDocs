package com.oleg.photodocs.domain.model

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.datasource.model.DocumentEntity
import com.oleg.photodocs.presentation.model.Document


data class DocumentModel(
    val icon: String,
    val id: Int,
    val name: String,
    val photoOptions: String
)

fun DocumentModel.mapToDataSource(): DocumentEntity = DocumentEntity (
    icon = this.icon,
    id = this.id,
    name = this.name,
    photoOptions = this.photoOptions
)

fun DocumentModel.mapToPresentation(): Document = Document (
    icon = this.icon,
    id = this.id,
    name = this.name,
    photoOptions = this.photoOptions
)

fun List<DocumentModel>.mapToPresentation(): List<Document> = map {
    it.mapToPresentation()
}


fun Resource<List<DocumentModel>>.mapToPresentation(): Resource<List<Document>> = Resource<List<Document>>(
    state = state,
    data = data?.mapToPresentation(),
    message = message
)

