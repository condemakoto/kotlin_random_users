package com.conde.kun.core.view

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<V>: ViewModel() {

    val viewState: MediatorLiveData<V> = MediatorLiveData<V>().apply { postValue(getInitialViewState()) }

    abstract fun getInitialViewState(): V

}