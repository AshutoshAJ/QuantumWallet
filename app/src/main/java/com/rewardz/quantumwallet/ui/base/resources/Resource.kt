package com.rewardz.quantumwallet.ui.base.resources

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val message: String) : Resource<T>()

    enum class Status {
        LOADING, SUCCESS, ERROR
    }

    val status: Status
    get() = when (this) {
        is Loading -> Status.LOADING
        is Success -> Status.SUCCESS
        is Error -> Status.ERROR
    }
}
