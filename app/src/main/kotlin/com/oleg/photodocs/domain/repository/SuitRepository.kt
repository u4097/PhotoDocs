package com.oleg.photodocs.domain.repository

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.SuitModel

interface SuitRepository {
    suspend fun get(refresh: Boolean): Resource<List<SuitModel>>?
}
