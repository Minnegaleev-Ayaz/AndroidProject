package com.example.feature_auth_impl.data.repository

import com.example.feature_auth_impl.data.exceptions.DifferentPasswordsException
import com.example.feature_auth_impl.domain.model.AuthUser
import com.example.feature_auth_impl.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.CoroutineDispatcher

class UserRepositoryImpl(
    private val auth: FirebaseAuth,
    private val dispatcher: CoroutineDispatcher
) : UserRepository {
    override suspend fun createUserWithEmailAndPassword(
        nickname: String,
        email: String,
        password: String,
        confPassword: String
    ): AuthUser {
        if (password!=confPassword){
            throw DifferentPasswordsException("qwerty")
        }
        val auth = FirebaseAuth.getInstance()
        val createUserTask = auth.createUserWithEmailAndPassword(email, password)

        createUserTask.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // User account created & signed in
                val user = auth.currentUser
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(nickname)
                    .build()
                return@addOnCompleteListener
                AuthUser(user?.uid ?: "", user?.email ?: "", user?.displayName ?: "")
            } else {
                // Sign in failed
                throw Exception("Error creating user: ${task.exception?.message}")
            }
        }
        throw Exception("Error creating user: Unknown error")
    }

    override suspend fun signInwithEmailAndPassword(nickname: String, password: String): AuthUser {
        TODO("Not yet implemented")
    }
}