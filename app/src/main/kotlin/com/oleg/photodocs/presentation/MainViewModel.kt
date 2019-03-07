package com.oleg.photodocs.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oleg.photodocs.AppConfiguration
import com.oleg.photodocs.networking.RemoteApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class MainViewModel : ViewModel(), CoroutineScope {

    private val mutableStates: MutableLiveData<State> = MutableLiveData()
    override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main
    val states: LiveData<State> = mutableStates

    init {
        mutableStates.value = State.Idle
    }

    fun refreshIfNecessary() {
        if (mutableStates.value is State.Idle || mutableStates.value is Error) {
            login()
        }
    }

    fun login() {
        mutableStates.value = State.Loading

        launch {

            Timber.v("Fetching games list...")
            val api: RemoteApi = AppConfiguration.api

            try {
                val response: LoginResponse = api.loginAsync().await()
                mutableStates.postValue(State.Success(response))
                Timber.v("Fetched games list successfully.")
            } catch (e: Exception) {
                Timber.e(e, "Something went wrong when fetching games list.")
                mutableStates.postValue(State.Error)
            }
        }
    }

    override fun onCleared() {
        coroutineContext.cancel()
    }

    sealed class State {
        object Idle : State()
        object Error : State()
        object Loading : State()
        data class Success(val response: LoginResponse) : State()
    }
}
