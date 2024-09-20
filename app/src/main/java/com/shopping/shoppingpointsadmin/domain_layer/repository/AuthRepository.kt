package com.shopping.shoppingpointsadmin.domain_layer.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.shopping.shoppingpointsadmin.common.states.SignOutState
import com.shopping.shoppingpointsadmin.domain_layer.models.Admin
import com.shopping.shoppingpointsadmin.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun loginUser(email: String, password: String): Flow<ResultState<AuthResult>>

    suspend fun registerUser(email: String, password: String): Flow<ResultState<AuthResult>>

    suspend fun saveAdminDetails(admin: Admin): Flow<ResultState<String>>

    suspend fun getAdminDetails(adminId: String): Flow<ResultState<Admin>>

    suspend fun signOut(): Flow<ResultState<String>>

    suspend fun getCurrentUser(): Flow<ResultState<FirebaseUser?>>

    suspend fun forgetPassword(email: String): Flow<ResultState<String>>
}