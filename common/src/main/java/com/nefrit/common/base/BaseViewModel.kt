package com.nefrit.common.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel : ViewModel() {
    val _errorFlow = MutableStateFlow<Throwable?>(null)
    val errorFlow: StateFlow<Throwable?>
        get() = _errorFlow

    suspend fun errorHandlling(throwable: Throwable) {
        _errorFlow.emit(throwable)
    }
}