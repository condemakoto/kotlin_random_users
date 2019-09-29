package com.conde.kun.core.domain

import com.conde.kun.core.error.BaseError

data class Resource<out T>(val status: Status, val data: T?, val error: BaseError?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(error: BaseError?): Resource<T> {
            return Resource(Status.ERROR, null, error)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}