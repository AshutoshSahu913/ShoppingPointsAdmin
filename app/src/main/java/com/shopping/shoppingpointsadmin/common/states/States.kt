package com.shopping.shoppingpointsadmin.common.states

import com.google.firebase.auth.AuthResult
import com.shopping.shoppingpointsadmin.domain_layer.models.Admin


data class LoginState(
    val isLoading: Boolean = false,
    val success: AuthResult?,
    val error: String = ""
)



data class RegisterState(
    val isLoading: Boolean = false,
    val success: AuthResult?,
    val error: String = ""
)

data class AdminState(
    val isLoading: Boolean = false,
    val success: String?=null,
    val admin: Admin? = null,
    val error: String = ""
)


data class SignOutState(
    val isLoading: Boolean = false,
    val success: String?,
    val error: String = ""
)


data class ForgetState(
    val isLoading: Boolean = false,
    val success: String?,
    val error: String = ""
)

