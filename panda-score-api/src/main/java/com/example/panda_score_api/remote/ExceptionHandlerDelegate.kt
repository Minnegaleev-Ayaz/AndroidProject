package com.example.panda_score_api.remote

import com.nefrit.common.core.resources.ResourceManager
import retrofit2.HttpException
import javax.inject.Inject

class ExceptionHandlerDelegate @Inject constructor(
    private val resourceManager: ResourceManager
) {

    fun handleException(ex: Throwable): Throwable {
        return when (ex) {
            is HttpException -> {
                when (ex.code()) {
                    else -> {
                        ex
                    }
                }
            }

            else -> {
                ex
            }
        }
    }
}