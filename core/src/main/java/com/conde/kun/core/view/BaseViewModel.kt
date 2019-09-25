package com.conde.kun.core.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<V>: ViewModel() {

    val viewState: LiveData<V> = MutableLiveData<V>().apply { postValue(getInitialViewState()) }

    abstract fun getInitialViewState(): V

}