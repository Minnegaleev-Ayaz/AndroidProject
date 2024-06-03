package com.example.feature_profile_impl.data.repository

import com.example.feature_profile_api.domain.repository.ProfileRepository
import com.example.feature_profile_api.domain.repository.model.DataUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.nefrit.common.utils.AsyncResult
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val reference: DatabaseReference,
) : ProfileRepository {
    override suspend fun getCurrentUser(flow: MutableStateFlow<AsyncResult<DataUser>?>) {
        var dataUser: DataUser? = null
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val user = dataSnapShot.getValue(DataUser::class.java)
                    if (user?.userId == firebaseAuth.currentUser!!.uid) {
                        flow.value = AsyncResult.Success(user)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                flow.value = AsyncResult.Error(error.toException())
            }
        })

    }
}