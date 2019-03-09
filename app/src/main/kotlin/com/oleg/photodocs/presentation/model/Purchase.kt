package com.oleg.photodocs.presentation.model

import com.oleg.photodocs.domain.model.CustomerModel
import com.oleg.photodocs.domain.model.PurchaseModel

data class Customer(
    val uuid: String
)

data class Purchase(
    val customer: Customer,
    val price: String
)

fun Customer.mapToDomain(): CustomerModel = CustomerModel(
    uuid = this.uuid
)

fun Purchase.mapToDomain(): PurchaseModel = PurchaseModel(
    customer = this.customer.mapToDomain(),
    price = this.price
)
