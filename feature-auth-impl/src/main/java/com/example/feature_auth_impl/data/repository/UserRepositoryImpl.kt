package com.example.feature_auth_impl.data.repository

import android.util.Log
import com.example.feature_auth_impl.R
import com.example.feature_auth_impl.data.exceptions.CreatingUserException
import com.example.feature_auth_impl.data.exceptions.DifferentPasswordsException
import com.example.feature_auth_impl.data.exceptions.EmptyValuesException
import com.example.feature_auth_impl.data.exceptions.ShortPasswordException
import com.example.feature_auth_impl.data.exceptions.SignInException
import com.example.feature_auth_impl.domain.model.AuthUser
import com.example.feature_auth_impl.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.nefrit.common.core.resources.ResourceManager
import kotlinx.coroutines.CoroutineDispatcher
import java.lang.reflect.InvocationTargetException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl @Inject constructor(
    private val resourceManager: ResourceManager,
    private val auth: FirebaseAuth,
    private val reference: DatabaseReference
) : UserRepository {
    override suspend fun createUserWithEmailAndPassword(
        nickname: String,
        email: String,
        password: String,
        confPassword: String
    ): AuthUser {
        if (nickname.isEmpty() || email.isEmpty() || password.isEmpty()){
            throw EmptyValuesException(resourceManager.getString(R.string.empty_fields))
        }
        if (password.length<6){
            throw ShortPasswordException(resourceManager.getString(R.string.short_password))
        }
        if (password != confPassword) {
            throw DifferentPasswordsException(resourceManager.getString(R.string.password_not_equal))
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
                                val hashMap: HashMap<String, String> = HashMap()
                                hashMap["userId"] = user.uid
                                hashMap["name"] = nickname
                                hashMap["mail"] = email
                                reference.push().setValue(hashMap)
                                continuation.resume(AuthUser(user?.uid?:"",user?.email ?: "", user?.displayName ?: ""))
                            } else {
                                throw CreatingUserException(resourceManager.getString(R.string.sign_in_fail))
                            }
                        }
                } else {
                    // Sign in failed
                    throw CreatingUserException(resourceManager.getString(R.string.sign_up_fail))
                }
            }
        }
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String): AuthUser {
        try {
            return suspendCoroutine { continuation ->
                val signInTask = auth.signInWithEmailAndPassword(email, password)
                signInTask.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = task.result?.user
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(user?.displayName)
                            .build()
                        continuation.resume(AuthUser(user?.uid ?: "", user?.email ?: "", user?.displayName ?: ""))
                    } else {
                        throw SignInException(resourceManager.getString(R.string.sign_in_fail))
                    }
                }
            }
        }catch (e:InvocationTargetException){
            throw SignInException(e.toString())
        }
    }
}