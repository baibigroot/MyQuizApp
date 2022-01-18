package com.example.myquizapp.core.network

class Resource<out T>(val status: Status, val data: T?, msg: String?, code: Int?) {

    companion object {
        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, null, null, null)
        }

        fun <T> success(data: T?, code: Int?): Resource<T> {
            return Resource(Status.SUCCESS, data, null, code)
        }

        fun <T> error(msg: String?, code: Int?): Resource<T> {
            return Resource(Status.ERROR, null, msg, code)
        }
    }
}