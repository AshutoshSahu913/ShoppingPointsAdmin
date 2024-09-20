package com.shopping.shoppingpointsadmin.presentation_layer.screens.authScreens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material.icons.outlined.TagFaces
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shopping.shoppingpointsadmin.R
import com.shopping.shoppingpointsadmin.domain_layer.models.Admin
import com.shopping.shoppingpointsadmin.presentation_layer.navigation.AdminRoutes
import com.shopping.shoppingpointsadmin.presentation_layer.navigation.AdminSubNavigation
import com.shopping.shoppingpointsadmin.presentation_layer.viewModel.AppViewModel
import com.shopping.shoppingpointsadmin.ui.theme.AppColor
import com.shopping.shoppingpointsadmin.ui.theme.BackgroundColor
import com.shopping.shoppingpointsadmin.utils.checkPasswordStrength
import com.shopping.shoppingpointsadmin.utils.encryptPassword

@Composable
fun SignUpScreen(
    navController: NavHostController, appViewModel: AppViewModel
) {
    val adminName = rememberSaveable {
        mutableStateOf("")
    }

    val adminEmail = rememberSaveable {
        mutableStateOf("")
    }

    val adminPhone = rememberSaveable {
        mutableStateOf("")
    }

    val errorName = rememberSaveable {
        mutableStateOf(false)
    }

    val errorPhone = rememberSaveable {
        mutableStateOf(false)
    }
    val errorEmail = rememberSaveable {
        mutableStateOf(false)
    }

    val adminPassword = rememberSaveable {
        mutableStateOf("")
    }
    val errorPassword = rememberSaveable {
        mutableStateOf(false)
    }

    val encryptedPassword = remember {
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    val registerState = appViewModel.registerUserState.value
    val saveState = appViewModel.saveResponse.value
//    val currentUser = appViewModel.currentUser.value


    LaunchedEffect(key1 = registerState/*, key2 = saveState*/) {
        // Show error message if there is one
        if (registerState.error.isNotEmpty()) {
            Toast.makeText(
                context,
                registerState.error,
                Toast.LENGTH_SHORT
            ).show()
        }


        // Show success message when registration is successful
        if (registerState.success != null) {
            val userId = appViewModel.currentUser.value?.uid

            if (userId?.isNotEmpty()!! && adminName.value.isNotEmpty() && adminPhone.value.isNotEmpty() && adminEmail.value.isNotEmpty() && adminPassword.value.isNotEmpty()) {
                val admin =
                    Admin(
                        adminId = userId,
                        name = adminName.value,
                        password = encryptPassword(adminPassword.value) ,
                        email = adminEmail.value,
                        phone = adminPhone.value
                    )
                appViewModel.saveAdminDetails(admin)
            }

            // On successful signup, navigate to home and clear the signup stack
            navController.navigate(AdminSubNavigation.AdminHomeMainNav)
            {
                popUpTo(AdminSubNavigation.AdminLoginNav) {
                    inclusive = true // Clears all login-related screens from back stack
                }
            }
            // Registration successful ka Toast show kar rahe hain
            Toast.makeText(
                context,
                "Register User Successfully!",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    LaunchedEffect(key1 = saveState) {


    }

    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .weight(0.35f)
                .background(
                    color = BackgroundColor,
                ), contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.shop2),
                contentDescription = "",
                modifier = Modifier
            )
        }

        Box(
            modifier = Modifier
                .padding(10.dp)
                .background(
                    color = Color.White, shape = RoundedCornerShape(40.dp)
                )
                .weight(0.65f)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    textAlign = TextAlign.Start,
                    text = "Admin Sign up",
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Black,
                    fontSize = 25.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .fillMaxWidth(),
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)

                ) {
                    Icon(
                        imageVector = Icons.Outlined.AlternateEmail,
                        contentDescription = "",
                        tint = Color.LightGray,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(5.dp)
                    )


                    TextField(
                        placeholder = {
                            Text(
                                text = "JohnDoe@gmail.com",
                                fontStyle = FontStyle.Normal,
                                fontFamily = FontFamily(
                                    Font(R.font.playpen_sans_medium)
                                ),
                                fontSize = 14.sp,
                                color = Color.LightGray
                            )
                        },
                        isError = errorEmail.value,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        textStyle = TextStyle(
                            fontFamily = FontFamily(Font(R.font.playpen_sans_medium)),
                            fontSize = 15.sp
                        ),
                        value = adminEmail.value,
                        onValueChange = { adminEmail.value = it },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            focusedIndicatorColor = AppColor,
                            focusedTextColor = Color.Black,
                            focusedLabelColor = AppColor,
                            errorContainerColor = Color.White,
                            errorIndicatorColor = Color.Red,
                            errorCursorColor = Color.Red,
                            errorTextColor = Color.Black,
                        ), modifier = Modifier
                            .fillMaxWidth()

                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.TagFaces,
                        contentDescription = "",
                        tint = Color.LightGray, modifier = Modifier.padding(5.dp)
                    )

                    TextField(
                        placeholder = {
                            Text(
                                text = "Admin Full Name",
                                fontStyle = FontStyle.Normal,
                                fontFamily = FontFamily(
                                    Font(R.font.playpen_sans_medium)
                                ),
                                fontSize = 14.sp,
                                color = Color.LightGray
                            )
                        },
                        isError = errorName.value,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        textStyle = TextStyle(
                            fontFamily = FontFamily(Font(R.font.playpen_sans_medium)),
                            fontSize = 15.sp
                        ),
                        value = adminName.value,
                        onValueChange = { adminName.value = it },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            focusedIndicatorColor = AppColor,
                            focusedTextColor = Color.Black,
                            focusedLabelColor = AppColor,
                            errorContainerColor = Color.White,
                            errorIndicatorColor = Color.Red,
                            errorCursorColor = Color.Red,
                            errorTextColor = Color.Black,

                            ), modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Call,
                        tint = Color.LightGray,
                        contentDescription = "",
                        modifier = Modifier.padding(5.dp)
                    )

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        placeholder = {
                            Text(
                                text = "Admin Phone",
                                fontStyle = FontStyle.Normal,
                                fontFamily = FontFamily(
                                    Font(R.font.playpen_sans_medium)
                                ),
                                fontSize = 14.sp,
                                color = Color.LightGray
                            )
                        },
                        isError = errorPhone.value,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        textStyle = TextStyle(
                            fontFamily = FontFamily(Font(R.font.playpen_sans_medium)),
                            fontSize = 15.sp
                        ),
                        value = adminPhone.value,
                        onValueChange = { number ->
                            if (number.length <= 10) {
                                adminPhone.value = number
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = AppColor,
                            focusedContainerColor = Color.White,
                            focusedTextColor = Color.Black,
                            focusedLabelColor = AppColor,
                            errorContainerColor = Color.White,
                            errorIndicatorColor = Color.Red,
                            errorCursorColor = Color.Red,

                            errorTextColor = Color.Black,
                        )
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Password,
                        tint = Color.LightGray,
                        contentDescription = "",
                        modifier = Modifier.padding(5.dp)
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        placeholder = {
                            Text(
                                text = "Password",
                                fontStyle = FontStyle.Normal,
                                fontFamily = FontFamily(
                                    Font(R.font.playpen_sans_medium)
                                ),
                                fontSize = 14.sp,
                                color = Color.LightGray
                            )
                        },
                        isError = errorPassword.value,
                        singleLine = true,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        textStyle = TextStyle(
                            fontFamily = FontFamily(Font(R.font.playpen_sans_medium)),
                            fontSize = 14.sp
                        ),
                        value = adminPassword.value,
                        onValueChange = {
                            adminPassword.value = it
                        },
                        trailingIcon = {
                            val image =
                                if (passwordVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff
                            IconButton(onClick = {
                                passwordVisible = !passwordVisible
                            }) {
                                Icon(
                                    imageVector = image,
                                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                                )
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = AppColor,
                            focusedContainerColor = Color.White,
                            focusedTextColor = Color.Black,
                            focusedLabelColor = AppColor,
                            errorContainerColor = Color.White,
                            errorIndicatorColor = Color.Red,
                            errorCursorColor = Color.Red,
                            errorTextColor = Color.Black,
                        )
                    )

                }

                val annotatedText = buildAnnotatedString {
                    append("By signing up, you're agree to our ")

                    // Annotate "Terms & Conditions"
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF0088FF),
                            textDecoration = TextDecoration.Underline,
                            fontSize = 10.sp
                        )
                    ) {
                        append("Terms & Conditions")
                    }


                    append(" and ")

                    // Annotate "Privacy Policy"1
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF0088FF),
                            textDecoration = TextDecoration.Underline,
                            fontSize = 10.sp
                        )
                    ) {
                        append("Privacy Policy")
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = annotatedText,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 10.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                if (registerState.isLoading /*&& saveState.isLoading*/) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = AppColor,
                            strokeCap = StrokeCap.Round,
                            strokeWidth = 2.dp
                        )
                    }

                } else {
                    ElevatedButton(
                        contentPadding = PaddingValues(horizontal = 100.dp),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            errorName.value = false
                            errorPhone.value = false
                            errorEmail.value = false
                            errorPassword.value = false

                            if (adminName.value.isEmpty() || adminPhone.value.isEmpty() || adminEmail.value.isEmpty()) {
                                if (adminName.value.isEmpty()) {
                                    errorName.value = true
                                }
                                if (adminPhone.value.isEmpty()) {
                                    errorPhone.value = true
                                }
                                if (adminEmail.value.isEmpty()) {
                                    errorEmail.value = true
                                }
                                if (adminPassword.value.isEmpty()) {
                                    errorPassword.value = true
                                }
                                Toast.makeText(context, "Fill all the Fields", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                val passwordStrengthMsg = checkPasswordStrength(adminPassword.value)
                                if (passwordStrengthMsg != null) {
                                    errorPassword.value = true
                                    Toast.makeText(
                                        context, passwordStrengthMsg, Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    encryptedPassword.value = encryptPassword(adminPassword.value)
                                    // all sign up process
                                    appViewModel.registerUser(
                                        email = adminEmail.value, password = adminPassword.value
                                    )


                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(AppColor)
                    ) {
                        Text(
                            text = "Sign up",
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(
                                Font(R.font.playpen_sans_medium)
                            ),
                            fontSize = 17.sp,
                            color = Color.White
                        )
                    }


                }

                val annotatedText1 = buildAnnotatedString {
                    append("Joined us before? ")
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF0088FF),
                            textDecoration = TextDecoration.None,
                            fontSize = 14.sp,
                        )
                    ) {
                        append("Login")
                    }
                }
                Text(
                    text = annotatedText1,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(AdminRoutes.LoginScreen)
                        }
                        .padding(10.dp)
                )
            }
        }
    }
}
