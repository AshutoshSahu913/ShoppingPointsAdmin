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
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.layout.ContentScale
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
import com.shopping.shoppingpointsadmin.presentation_layer.viewModel.AppViewModel
import com.shopping.shoppingpointsadmin.ui.theme.AppColor
import com.shopping.shoppingpointsadmin.ui.theme.BackgroundColor
import com.shopping.shoppingpointsadmin.ui.theme.Color1

@Composable
fun LoginScreen(navController: NavHostController, appViewModel: AppViewModel) {

    val adminEmail = remember {
        mutableStateOf("")
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
                painter = painterResource(id = R.drawable.shop4),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
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
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    textAlign = TextAlign.Start,
                    text = "Admin Login",
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
                            focusedIndicatorColor = AppColor,
                            focusedContainerColor = Color.White,
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
                        imageVector = Icons.Outlined.Lock,
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
                            focusedContainerColor = Color.White,
                            focusedTextColor = Color.Black,
                            focusedIndicatorColor = AppColor,
                            focusedLabelColor = AppColor,
                            errorContainerColor = Color.White,
                            errorIndicatorColor = Color.Red,
                            errorCursorColor = Color.Red,
                            errorTextColor = Color.Black,
                        )
                    )

                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Forget Password",
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color1,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(AdminRoutes.ForgetPasswordScreen)
                        }
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))
                ElevatedButton(
                    contentPadding = PaddingValues(),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        errorEmail.value = false
                        errorPassword.value = false

                        if (adminEmail.value.isEmpty() || adminPassword.value.isEmpty()) {
                            if (adminEmail.value.isEmpty()) {
                                errorEmail.value = true
                            }
                            if (adminPassword.value.isEmpty()) {
                                errorPassword.value = true
                            }
                            Toast.makeText(context, "Fill all the Fields", Toast.LENGTH_SHORT)
                                .show()

                        }
                    },
                    colors = ButtonDefaults.buttonColors(AppColor)
                ) {
                    Text(
                        text = "Login",
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(
                            Font(R.font.playpen_sans_medium)
                        ),
                        fontSize = 17.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {

                    HorizontalDivider(modifier = Modifier.weight(0.4f), color = Color.LightGray)
                    Text(
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(0.2f),
                        text = " OR ",
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily(
                            Font(R.font.playpen_sans_medium)
                        ),
                        fontSize = 14.sp,
                        color = Color.LightGray
                    )
                    HorizontalDivider(modifier = Modifier.weight(0.4f), color = Color.LightGray)
                }

                Spacer(modifier = Modifier.height(10.dp))
                ElevatedButton(
                    contentPadding = PaddingValues(horizontal = 10.dp),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFF1F5F6)),
                    elevation = ButtonDefaults.elevatedButtonElevation(1.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(4.dp)
                                .size(24.dp)
                        )
                        Text(
                            text = "Login with Google",
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(
                                Font(R.font.playpen_sans_medium)
                            ),
                            fontSize = 15.sp,
                            color = Color.Black, modifier = Modifier
                        )
                    }
                }


                val annotatedText1 = buildAnnotatedString {
                    append("New to Here ? ")

                    withStyle(
                        style = SpanStyle(
                            color = Color1,
                            textDecoration = TextDecoration.None,
                            fontSize = 14.sp,
                        )
                    ) {
                        append("Register")
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
                            navController.navigate(AdminRoutes.SignUpScreen)
                        }
                        .padding(10.dp)
                )
            }
        }
    }
}