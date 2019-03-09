package com.oleg.photodocs.presentation.model

import com.oleg.photodocs.domain.model.SuitModel


data class Suit(
    val id: Int,
    val name: String,
    val suit: String
)

fun Suit.mapToDomain(): SuitModel = SuitModel(
    id = this.id,
    name = this.name,
    suit = this.suit
)
