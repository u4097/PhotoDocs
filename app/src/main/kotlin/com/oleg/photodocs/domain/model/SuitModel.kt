package com.oleg.photodocs.domain.model

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.datasource.model.SuitEntity
import com.oleg.photodocs.presentation.model.Suit

data class SuitModel(
    val id: Int,
    val name: String,
    val suit: String
)

fun SuitModel.mapToDataSource(): SuitEntity = SuitEntity (
    id = this.id,
    name = this.name,
    suit = this.suit
)

fun SuitModel.mapToPresentation(): Suit = Suit (
    id = this.id,
    name = this.name,
    suit = this.suit
)

fun List<SuitModel>.mapToPresentation(): List<Suit> = map {
    it.mapToPresentation()
}


fun Resource<List<SuitModel>>.mapToPresentation(): Resource<List<Suit>> = Resource<List<Suit>>(
    state = state,
    data = data?.mapToPresentation(),
    message = message
)

