package com.example.feature_auth_impl.domain.mapper

import com.example.feature_auth_impl.domain.model.AuthUser
import com.example.feature_auth_impl.presentation.model.UserUiModel
import javax.inject.Inject

class UserUiModelMapper @Inject constructor() {
    fun mapFromDataToUI(authUser: AuthUser): UserUiModel {
        return UserUiModel(
            id = authUser.id,
            name = authUser.nickname,
            email = authUser.email
            )
    }
}