package com.conde.kun.core.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseUseCase<T, P> () {

    lateinit var coroutineScope: CoroutineScope

    constructor(coroutineScope: CoroutineScope) : this() {
        this.coroutineScope = coroutineScope
    }

    fun execute(coroutineScope: CoroutineScope, param: P): LiveData<Resource<T>> {
        this.coroutineScope = coroutineScope
        return execute(param)
    }

    private fun execute(param: P): LiveData<Resource<T>> {
        val response: MutableLiveData<Resource<T>> = MutableLiveData()
        response.postValue(Resource.loading(null))
        coroutineScope.launch {
            try {
                val data = getData(param)
                response.postValue(Resource.success(data))
            } catch(ex: Exception) {
                //TODO: get the error and set the response in the liveData.
                response.postValue(Resource.error("service error", null))
            }
        }

        return response
    }

    abstract suspend fun getData(param: P): T

}