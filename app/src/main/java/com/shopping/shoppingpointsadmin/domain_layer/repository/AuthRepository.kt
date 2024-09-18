package com.shopping.shoppingpointsadmin.domain_layer.repository

import com.google.firebase.auth.AuthResult
import com.shopping.shoppingpointsadmin.domain_layer.models.Admin
import com.shopping.shoppingpointsadmin.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(email: String, password: String): Flow<ResultState<AuthResult>>

    fun registerUser(email: String, password: String): Flow<ResultState<AuthResult>>

    fun saveAdminDetails(admin: Admin): Flow<ResultState<String>>
}