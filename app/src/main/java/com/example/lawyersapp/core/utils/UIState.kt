package com.example.lawyersapp.core.utils

sealed class UIState<T> {
    class Error<T>(val error: String): UIState<T>()
    class Loading<T> : UIState<T>()
    class Success<T>(val data: T): UIState<T>()
}
