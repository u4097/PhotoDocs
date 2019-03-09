package com.oleg.photodocs.domain.usecases

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.SuitModel
import com.oleg.photodocs.domain.repository.SuitRepository


class SuitUseCase constructor(private val suitRepository: SuitRepository) {

    suspend fun get(refresh: Boolean): Resource<List<SuitModel>>? =
        suitRepository.get(refresh)

}
