package com.shopping.shoppingpointsadmin.presentation_layer.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.shopping.shoppingpointsadmin.common.states.AdminState
import com.shopping.shoppingpointsadmin.common.states.ForgetState
import com.shopping.shoppingpointsadmin.common.states.LoginState
import com.shopping.shoppingpointsadmin.common.states.RegisterState
import com.shopping.shoppingpointsadmin.common.states.SignOutState
import com.shopping.shoppingpointsadmin.domain_layer.models.Admin
import com.shopping.shoppingpointsadmin.domain_layer.repository.AuthRepository
import com.shopping.shoppingpointsadmin.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    //user Registration
    private val _registerUserState: MutableState<RegisterState> =
        mutableStateOf(RegisterState(success = null))
    val registerUserState = _registerUserState

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            repository.registerUser(email = email, password = password).collectLatest { result ->
                when (result) {
                    is ResultState.Error -> {
                        Log.d("VIEWMODEL", "registerUser: Error - ${result.exception}")
                        _registerUserState.value =
                            RegisterState(
                                isLoading = false,
                                error = result.exception,
                                success = null
                            )
                    }

                    ResultState.Loading -> {
                        Log.d("VIEWMODEL", "registerUser: Loading")
                        _registerUserState.value =
                            RegisterState(isLoading = true, success = null)
                    }

                    is ResultState.Success -> {
                        Log.d("VIEWMODEL", "registerUser: Success - ${result.data}")
                        _registerUserState.value =
                            RegisterState(isLoading = false, success = result.data)
                        getCurrentUser()
                        getAdminDetails(result.data.user?.uid.toString())
                    }
                }
            }
        }
    }

    //Admin Login
    private val _loginState: MutableState<LoginState> = mutableStateOf(LoginState(success = null))
    val loginState = _loginState

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            repository.loginUser(email = email, password = password).collectLatest { result ->
                when (result) {
                    is ResultState.Error -> {
                        Log.d("VIEWMODEL", "loginUser: Error - ${result.exception}")
                        _loginState.value = LoginState(
                            error = result.exception,
                            success = null,
                            isLoading = false
                        )
                    }

                    ResultState.Loading -> {
                        Log.d("VIEWMODEL", "loginUser: Loading")
                        _loginState.value = LoginState(
                            success = null,
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        Log.d("VIEWMODEL", "loginUser: Success - ${result.data}")
                        _loginState.value = LoginState(
                            success = result.data,
                            isLoading = false
                        )
                        getCurrentUser()
                        getAdminDetails(result.data.user?.uid.toString())
                    }
                }
            }
        }
    }

    //Admin Sign Out
    private val _signOut: MutableState<SignOutState> =
        mutableStateOf(SignOutState(success = null))
    val signOut = _signOut

    fun signOut() {
        viewModelScope.launch {
            repository.signOut().collect { result ->
                when (result) {
                    is ResultState.Error -> {
                        Log.d("VIEWMODEL", "signOut: Error - ${result.exception}")
                        _signOut.value = SignOutState(error = result.exception, success = null)
                    }

                    ResultState.Loading -> {
                        Log.d("VIEWMODEL", "signOut: Loading")
                        _signOut.value = SignOutState(isLoading = true, success = null)
                    }

                    is ResultState.Success -> {
                        Log.d("VIEWMODEL", "signOut: Success - ${result.data}")
                        _signOut.value = SignOutState(isLoading = false, success = result.data)
                    }
                }
            }
        }
    }

    //save admin details
    private val _saveResponse: MutableState<AdminState> =
        mutableStateOf(AdminState(isLoading = false))
    val saveResponse = _saveResponse

    fun saveAdminDetails(admin: Admin) {
        viewModelScope.launch {
            repository.saveAdminDetails(admin).collect { result ->
                when (result) {
                    is ResultState.Error -> {
                        Log.d("VIEWMODEL", "saveAdminDetails: Error - ${result.exception}")
                        _saveResponse.value =
                            AdminState(
                                error = result.exception,
                                isLoading = false,
                                success = null
                            )
                    }

                    ResultState.Loading -> {
                        Log.d("VIEWMODEL", "saveAdminDetails: Loading")
                        _saveResponse.value =
                            AdminState(isLoading = true, success = null)
                    }

                    is ResultState.Success -> {
                        Log.d("VIEWMODEL", "saveAdminDetails: Success - ${result.data}")
                        _saveResponse.value =
                            AdminState(success = result.data, isLoading = false)
                    }
                }
            }
        }
    }

    // Get admin details state
    private val _getAdminResponse: MutableState<AdminState> =
        mutableStateOf(AdminState(success = null))
    val getAdminResponse = _getAdminResponse

    fun getAdminDetails(adminId: String) {
        viewModelScope.launch {
            repository.getAdminDetails(adminId).collect { result ->
                when (result) {
                    is ResultState.Error -> {
                        Log.d("VIEWMODEL", "getAdminDetails: Error - ${result.exception}")
                        _getAdminResponse.value =
                            AdminState(
                                error = result.exception,
                                isLoading = false,
                                success = null
                            )
                    }

                    ResultState.Loading -> {
                        Log.d("VIEWMODEL", "getAdminDetails: Loading")
                        _getAdminResponse.value =
                            AdminState(isLoading = true, success = null)
                    }

                    is ResultState.Success -> {
                        Log.d("VIEWMODEL", "getAdminDetails: Success - ${result.data.adminId}")
                        _getAdminResponse.value =
                            AdminState(
                                admin = result.data,
                                isLoading = false,
                                success = result.data.adminId
                            )
                    }
                }
            }
        }
    }

    //get current user
    private val _currentUser: MutableState<FirebaseUser?> = mutableStateOf(null)
    val currentUser = _currentUser

    fun getCurrentUser() {
        viewModelScope.launch {
            repository.getCurrentUser().collect { result ->
                when (result) {
                    is ResultState.Error -> {
                        Log.d("VIEWMODEL", "getCurrentUser: Error - ${result.exception}")
                    }

                    ResultState.Loading -> {
                        Log.d("VIEWMODEL", "getCurrentUser: Loading")
                    }

                    is ResultState.Success -> {
                        Log.d("VIEWMODEL", "getCurrentUser: Success - ${result.data}")
                        _currentUser.value = result.data
                    }
                }
            }
        }
    }

    //forget password
    private val _forgetState: MutableState<ForgetState> =
        mutableStateOf(ForgetState(success = null))
    val forgetState = _forgetState

    fun forgetPassword(email: String) {
        viewModelScope.launch {
            repository.forgetPassword(email).collect { result ->
                when (result) {
                    is ResultState.Error -> {
                        Log.d("VIEWMODEL", "forgetPassword: Error - ${result.exception}")
                        _forgetState.value =
                            ForgetState(isLoading = false, error = result.exception, success = null)
                    }

                    ResultState.Loading -> {
                        Log.d("VIEWMODEL", "forgetPassword: Loading")
                        _forgetState.value =
                            ForgetState(isLoading = true, success = null)
                    }

                    is ResultState.Success -> {
                        Log.d("VIEWMODEL", "forgetPassword: Success - ${result.data}")
                        _forgetState.value =
                            ForgetState(isLoading = false, success = result.data)
                    }
                }
            }
        }
    }
}
