package com.conde.kun.randomusers.view.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class SplashViewModel: ViewModel() {

    val viewState: MutableLiveData<SplashViewState> = MutableLiveData<SplashViewState>().apply { postValue(getInitialViewState()) }

    fun getInitialViewState(): SplashViewState {
        val viewState = SplashViewState()
        viewState.showSplash = false
        return viewState
    }

    fun onViewInit() {
        viewModelScope.launch {
            delay(2000L)
            viewState.value?.showSplash = true
            viewState.postValue(viewState.value)
        }
    }

}