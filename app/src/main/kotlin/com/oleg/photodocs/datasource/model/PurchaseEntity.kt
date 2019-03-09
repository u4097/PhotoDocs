package com.oleg.photodocs.datasource.model

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.CustomerModel
import com.oleg.photodocs.domain.model.PurchaseModel
import com.squareup.moshi.Json

data class CustomerEntity(
    @field:Json(name = "uuid")
    val uuid: String
)

data class PurchaseEntity(
    @field:Json(name = "customer")
    val customer: CustomerEntity,
    @field:Json(name = "price")
    val price: String
)

fun CustomerEntity.mapToDomain():CustomerModel = CustomerModel(
    uuid = this.uuid
)


fun PurchaseEntity.mapToDomain(): PurchaseModel = PurchaseModel(
    customer = this.customer.mapToDomain(),
    price = this.price
)


fun List<PurchaseEntity>.mapToDomain(): List<PurchaseModel> = map {
    it.mapToDomain()
}


fun Resource<List<PurchaseEntity>>.mapToDomain(): Resource<List<PurchaseModel>> = Resource<List<PurchaseModel>>(
    state = state,
    data = data?.mapToDomain(),
    message = message
)

