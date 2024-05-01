package com.example.feature_predict_impl.data

import com.example.feature_predict_impl.data.exceptions.UserNotAuthorized
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
                    401 -> {
                        ex
                    }

                    403 -> {
                        ex
                    }

                    429 -> {
                        ex
                    }

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