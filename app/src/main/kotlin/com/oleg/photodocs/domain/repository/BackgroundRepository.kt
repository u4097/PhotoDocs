package com.oleg.photodocs.domain.repository

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.BackgroundModel

interface BackgroundRepository {
    suspend fun get(refresh: Boolean): Resource<List<BackgroundModel>>?
}
