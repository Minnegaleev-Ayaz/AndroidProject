package com.example.feature_auth_impl.presentation.model

import android.provider.ContactsContract.CommonDataKinds.Email

data class UserUiModel(
    val id: String,
    val name: String,
    val email: String
)
