package com.example.feature_auth_impl

interface UsersAuthRouter {
    fun openUserAuth(userId: Int)

    fun returnToUsersAuth()
}