package com.example.feature_auth_impl.data.repository

import android.util.Log
import com.example.feature_auth_impl.data.exceptions.DifferentPasswordsException
import com.example.feature_auth_impl.domain.model.AuthUser
import com.example.feature_auth_impl.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : UserRepository {
    override suspend fun createUserWithEmailAndPassword(
        nickname: String,
        email: String,
        password: String,
        confPassword: String
    ): AuthUser {
        if (password != confPassword) {
            throw DifferentPasswordsException("qwerty")
        }

        val createUserTask = auth.createUserWithEmailAndPassword(email, password)

        return suspendCoroutine { continuation ->
            createUserTask.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // User account created & signed in
                    val user = auth.currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(nickname)
                        .build()
                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { updateTask ->
                            if (updateTask.isSuccessful) {
                                continuation.resume(AuthUser(user?.email ?: "", user?.displayName ?: ""))
                            } else {
                                continuation.resumeWithException(Exception("Error updating user profile: ${updateTask.exception?.message}"))
                            }
                        }
                } else {
                    // Sign in failed
                    continuation.resumeWithException(Exception("Error creating user: ${task.exception?.message}"))
                }
            }
        }
    }

    override suspend fun signInwithEmailAndPassword(email: String, password: String): AuthUser {
        val signInTask = auth.signInWithEmailAndPassword(email, password)

        return suspendCoroutine { continuation ->
            signInTask.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(user?.displayName)
                        .build()
                    continuation.resume(AuthUser(user?.email ?: "", user?.displayName ?: ""))
                } else {
                    continuation.resumeWithException(Exception("Error signing in: ${task.exception?.message}"))
                }
            }
        }
    }
}