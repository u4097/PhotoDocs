package com.oleg.photodocs.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.mapToPresentation
import com.oleg.photodocs.domain.usecases.BackgroundUseCase
import com.oleg.photodocs.presentation.model.Background
import kotlinx.coroutines.launch

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-07
 * Time: 22:22
 */


class BackgroundViewModel constructor(val backgroundUseCase: BackgroundUseCase) : AbstractViewModel() {

    val backgroundsData = MutableLiveData<Resource<List<Background>>>()


    fun getBackgrounds(refresh: Boolean) {
        scope.launch {
            val response = backgroundUseCase.get(refresh)
            backgroundsData.postValue(response?.mapToPresentation())
        }
    }

}