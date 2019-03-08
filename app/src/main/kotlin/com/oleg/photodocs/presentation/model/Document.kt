package com.oleg.photodocs.presentation.model

import com.oleg.photodocs.domain.model.DocumentModel


data class Document(
    val icon: String,
    val id: Int,
    val name: String,
    val photoOptions: String
)

fun Document.mapToDomain(): DocumentModel = DocumentModel (
    icon = this.icon,
    id = this.id,
    name = this.name,
    photoOptions = this.photoOptions
)
