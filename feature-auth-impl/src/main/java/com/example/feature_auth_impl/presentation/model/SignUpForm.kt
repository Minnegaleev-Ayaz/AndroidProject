package com.example.feature_auth_impl.presentation.model

data class SignUpForm(
    val nickname: String,
    val email: String,
    val password: String,
    val confPassword: String
)