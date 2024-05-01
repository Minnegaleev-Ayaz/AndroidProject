package com.example.feature_auth_impl

interface UsersAuthRouter {
    fun openSignUpFromSignIn()

    fun openSignInFromSignUp()
    fun openPredictionFromSignUp()
    fun openPredictionFromSignIn()
}