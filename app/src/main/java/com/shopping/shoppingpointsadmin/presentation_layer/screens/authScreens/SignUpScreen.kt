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
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.shopping.shoppingpointsadmin.presentation_layer.navigation.AdminRoutes
import com.shopping.shoppingpointsadmin.ui.theme.AppColor
import com.shopping.shoppingpointsadmin.ui.theme.BackgroundColor
import com.shopping.shoppingpointsadmin.utils.encryptPassword
import com.shopping.shoppingpointsadmin.utils.isPasswordStrong

@Composable
fun SignUpScreen(navController: NavHostController, navController1: NavHostController) {

    val adminName = remember {
        mutableStateOf("")
    }

    val adminEmail = remember {
        mutableStateOf("")
    }

    val adminPhone = remember {
        mutableStateOf("")
    }

    val errorName = remember {
        mutableStateOf(false)
    }

    val errorPhone = remember {
        mutableStateOf(false)
    }
    val errorEmail = remember {
        mutableStateOf(false)
    }

    val adminPassword = remember {
        mutableStateOf("")
    }
    val errorPassword = remember {
        mutableStateOf(false)
    }

    var passwordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current
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
                    color = Color.White,
                    shape = RoundedCornerShape(40.dp)
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
                            fontSize = 12.sp
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
                            fontSize = 12.sp
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
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 10.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))
                ElevatedButton(
                    contentPadding = PaddingValues(horizontal = 100.dp),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth(),
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
                        } else if (!isPasswordStrong(adminPassword.value)) {
                            errorPassword.value = true
                            Toast.makeText(context, "Password must be strong", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            val encryptedPassword = encryptPassword(adminPassword.value)
                            // Save the encrypted password securely (e.g., send it to your server or store it locally)
                            Toast.makeText(
                                context,
                                "Login successful with encrypted password: $encryptedPassword",
                                Toast.LENGTH_SHORT
                            ).show()

                            // all sign up process
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

                val annotatedText1 = buildAnnotatedString {
                    append("Joined us before? ")

                    // Annotate "Terms & Conditions"
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
                        }.padding(10.dp)


                )
            }

        }
    }
}
