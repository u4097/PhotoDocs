package com.oleg.photodocs.domain.usecases

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.BackgroundModel
import com.oleg.photodocs.domain.repository.BackgroundRepository


class BackgroundUseCase constructor(private val backgroundRepository: BackgroundRepository) {

    suspend fun get(refresh: Boolean): Resource<List<BackgroundModel>>? =
        backgroundRepository.get(refresh)

}
