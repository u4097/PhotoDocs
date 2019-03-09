package com.oleg.photodocs.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.mapToPresentation
import com.oleg.photodocs.domain.usecases.SuitUseCase
import com.oleg.photodocs.presentation.model.Suit
import kotlinx.coroutines.launch

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-07
 * Time: 22:22
 */


class SuitViewModel constructor(val documentUseCase: SuitUseCase) : AbstractViewModel() {

    val suitsData = MutableLiveData<Resource<List<Suit>>>()


    fun getSuits(refresh: Boolean) {
        scope.launch {
            val response = documentUseCase.get(refresh)
            suitsData.postValue(response?.mapToPresentation())
        }
    }

}