package com.oleg.photodocs.domain.model

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.datasource.model.CustomerEntity
import com.oleg.photodocs.datasource.model.PurchaseEntity
import com.oleg.photodocs.presentation.model.Customer
import com.oleg.photodocs.presentation.model.Purchase


data class CustomerModel(
    val uuid: String
)

data class PurchaseModel(
    val customer: CustomerModel,
    val price: String
)

fun CustomerModel.mapToDataSource(): CustomerEntity = CustomerEntity(
    uuid = this.uuid
)

fun PurchaseModel.mapToDataSource(): PurchaseEntity = PurchaseEntity(
    customer = this.customer.mapToDataSource(),
    price = this.price
)

fun CustomerModel.mapToPresentation(): Customer = Customer(
    uuid = this.uuid
)


fun PurchaseModel.mapToPresentation(): Purchase = Purchase(
    customer = this.customer.mapToPresentation(),
    price = this.price
)

fun List<PurchaseModel>.mapToPresentation(): List<Purchase> = map {
    it.mapToPresentation()
}


fun Resource<List<PurchaseModel>>.mapToPresentation(): Resource<List<Purchase>> = Resource<List<Purchase>>(
    state = state,
    data = data?.mapToPresentation(),
    message = message
)



