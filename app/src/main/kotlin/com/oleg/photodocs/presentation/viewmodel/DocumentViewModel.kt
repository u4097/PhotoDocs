package com.oleg.photodocs.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.mapToPresentation
import com.oleg.photodocs.domain.usecases.DocumentUseCase
import com.oleg.photodocs.presentation.model.Document
import com.oleg.photodocs.presentation.utils.livedata.SingleLiveEvent
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-07
 * Time: 22:22
 */


class DocumentViewModel constructor(val documentUseCase: DocumentUseCase) : AbstractViewModel() {

    val documents = SingleLiveEvent<Resource<List<Document>>>()

    fun getDocuments(refresh: Boolean = false) {
        scope.launch {
            val response = documentUseCase.get(refresh)
            documents.postValue(response?.mapToPresentation())
        }
    }

}