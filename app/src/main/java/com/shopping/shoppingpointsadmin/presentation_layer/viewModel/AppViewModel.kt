package com.shopping.shoppingpointsadmin.presentation_layer.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shopping.shoppingpointsadmin.common.states.LoginState
import com.shopping.shoppingpointsadmin.common.states.RegisterState
import com.shopping.shoppingpointsadmin.common.states.SaveAdminState
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
                        _registerUserState.value =
                            RegisterState(
                                isLoading = false,
                                error = result.exception,
                                success = null
                            )
                    }

                    ResultState.Loading -> {
                        _registerUserState.value =
                            RegisterState(isLoading = true, success = null)
                    }

                    is ResultState.Success -> {
                        _registerUserState.value =
                            RegisterState(isLoading = false, success = result.data)
                    }
                }

            }
        }
    }

    //user Login
    private val _loginState:
            MutableState<LoginState> = mutableStateOf(LoginState(success = null))

    val loginState = _loginState

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            repository.loginUser(email = email, password = password).collectLatest { result ->
                when (result) {
                    is ResultState.Error -> {
                        _loginState.value = LoginState(
                            error = result.exception,
                            success = null,
                            isLoading = false
                        )
                    }

                    ResultState.Loading -> {
                        _loginState.value = LoginState(
                            success = null,
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _loginState.value = LoginState(
                            success = result.data,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }


    private val _saveResponse: MutableState<SaveAdminState> =
        mutableStateOf(SaveAdminState(isLoading = true, success = null))
    val saveResponse = _saveResponse

    fun saveAdminDetails(admin: Admin) {
        viewModelScope.launch {
            repository.saveAdminDetails(admin).collect { result ->
                when (result) {
                    is ResultState.Error -> {
                        _saveResponse.value =
                            SaveAdminState(
                                error = result.exception,
                                isLoading = false,
                                success = null
                            )
                    }

                    ResultState.Loading -> {
                        _saveResponse.value =
                            SaveAdminState(isLoading = true, success = null)
                    }

                    is ResultState.Success -> {
                        _saveResponse.value =
                            SaveAdminState(success = result.data, isLoading = false)
                    }
                }
            }
        }
    }

}