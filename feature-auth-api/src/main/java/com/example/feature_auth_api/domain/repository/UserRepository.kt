package com.example.feature_auth_impl.domain.repository

import com.example.feature_auth_impl.domain.model.AuthUser

interface UserRepository {
    suspend fun createUserWithEmailAndPassword(nickname: String, email:String,password:String,confPassword:String):AuthUser
    suspend fun signInWithEmailAndPassword(email: String,password: String):AuthUser
}