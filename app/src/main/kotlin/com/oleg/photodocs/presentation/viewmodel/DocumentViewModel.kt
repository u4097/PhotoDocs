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


class DocumentViewModel constructor(val documentUseCase: DocumentUseCase) : ViewModel() {


    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)


    val documents = SingleLiveEvent<Resource<List<Document>>>()



    fun getDocuments(refresh: Boolean) {
        scope.launch {
            val response = documentUseCase.get(refresh)
            documents.postValue(response?.mapToPresentation())
        }
    }


    private fun cancelAllRequests() = coroutineContext.cancel()

    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }

}