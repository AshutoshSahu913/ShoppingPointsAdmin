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
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shopping.shoppingpointsadmin.R
import com.shopping.shoppingpointsadmin.presentation_layer.navigation.AdminRoutes
import com.shopping.shoppingpointsadmin.presentation_layer.viewModel.AppViewModel
import com.shopping.shoppingpointsadmin.ui.theme.AppColor
import com.shopping.shoppingpointsadmin.ui.theme.BackgroundColor

@Composable
fun ForgetPasswordScreen(navController: NavHostController, appViewModel: AppViewModel) {


    val adminEmail = rememberSaveable {
        mutableStateOf("")
    }

    val errorEmail = rememberSaveable {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    val forgetState = appViewModel.forgetState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .padding(top = 20.dp)
                .weight(0.4f)
                .background(
                    color = Color.White,
                ), contentAlignment = Alignment.BottomEnd
        ) {


            Image(
                painter = painterResource(id = R.drawable.forget),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
            Icon(
                imageVector = Icons.Default.ChevronLeft,
                contentDescription = "",
                modifier = Modifier
                    .padding(10.dp)
                    .background(BackgroundColor, RoundedCornerShape(10.dp))
                    .size(40.dp)
                    .align(
                        Alignment.TopStart
                    )
                    .clickable {
                        navController.navigateUp()
                    }
            )
        }


        Box(
            modifier = Modifier
                .padding(10.dp)
                .background(
                    color = Color.White, shape = RoundedCornerShape(40.dp)
                )
                .weight(0.6f)
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
                    text = "Forget\nPassword?",
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Black,
                    fontSize = 40.sp,
                    lineHeight = TextUnit(40f, TextUnitType.Sp),
                    color = Color.Black,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .fillMaxWidth(),
                )
                Text(
                    text = "Don't worry! It happens. Please enter the address associated with your account.",
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()


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
                            focusedIndicatorColor = AppColor,
                            unfocusedLabelColor = AppColor,
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            focusedTextColor = Color.Black,
                            focusedLabelColor = AppColor,
                            errorContainerColor = Color.White,
                            errorIndicatorColor = Color.Red,
                            errorCursorColor = Color.Red,
                            errorTextColor = Color.Black,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                if (forgetState.isLoading) {
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
                        contentPadding = PaddingValues(horizontal = 10.dp),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            errorEmail.value = false
                            if (adminEmail.value.isEmpty()) {
                                if (adminEmail.value.isEmpty()) {
                                    errorEmail.value = true
                                }
                                Toast.makeText(context, "Fill all the Fields", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                appViewModel.forgetPassword(email = adminEmail.value)
                                if (forgetState.success != null) {
                                    // Show error message if there is one
                                    navController.navigate(AdminRoutes.LoginScreen) {
                                        popUpTo(AdminRoutes.ForgetPasswordScreen) {
                                            inclusive = true
                                        }
                                    }

                                    Toast.makeText(
                                        context,
                                        "Check Your Mail",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }

                        },
                        colors = ButtonDefaults.buttonColors(AppColor),
                        elevation = ButtonDefaults.elevatedButtonElevation(1.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "Submit",
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily(
                                    Font(R.font.playpen_sans_medium)
                                ),
                                fontSize = 17.sp,
                                color = Color.White,
                                modifier = Modifier
                            )
                        }
                    }

                    if (forgetState.error.isNotEmpty()) {
                        // Show error message if there is one
                        Toast.makeText(context, forgetState.error, Toast.LENGTH_SHORT)
                            .show()
                    }

                }



            }
        }
    }
}