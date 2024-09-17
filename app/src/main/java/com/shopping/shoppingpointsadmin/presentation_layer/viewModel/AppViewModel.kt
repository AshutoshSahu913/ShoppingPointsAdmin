package com.shopping.shoppingpointsadmin.presentation_layer.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shopping.shoppingpointsadmin.common.states.RegisterState
import com.shopping.shoppingpointsadmin.domain_layer.repository.AuthRepository
import com.shopping.shoppingpointsadmin.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private val _registerUserResponse: MutableState<RegisterState> =
        mutableStateOf(RegisterState(success = null))
    val registerUserResponse = _registerUserResponse

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            repository.registerUser(email = email, password = password).collect { result ->
                when (result) {
                    is ResultState.Error -> {
                        _registerUserResponse.value =
                            RegisterState(
                                isLoading = false,
                                error = result.exception,
                                success = null
                            )
                    }

                    ResultState.Loading -> {
                        _registerUserResponse.value =
                            RegisterState(isLoading = true, success = null)
                    }

                    is ResultState.Success -> {
                        _registerUserResponse.value =
                            RegisterState(isLoading = false, success = result.data)
                    }
                }

            }
        }
    }

}