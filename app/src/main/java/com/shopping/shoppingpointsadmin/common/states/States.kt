package com.shopping.shoppingpointsadmin.common.states

import com.google.firebase.auth.AuthResult


data class LoginState(
    val isLoading: Boolean = false,
    val success: String = "",
    val error: String = ""
)


data class RegisterState(
    val isLoading: Boolean = false,
    val success: AuthResult?,
    val error: String = ""
)