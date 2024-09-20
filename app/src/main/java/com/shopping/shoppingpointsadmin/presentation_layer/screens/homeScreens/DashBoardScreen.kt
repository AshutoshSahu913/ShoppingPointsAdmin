package com.shopping.shoppingpointsadmin.presentation_layer.screens.homeScreens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shopping.shoppingpointsadmin.R
import com.shopping.shoppingpointsadmin.presentation_layer.navigation.AdminSubNavigation
import com.shopping.shoppingpointsadmin.presentation_layer.viewModel.AppViewModel
import com.shopping.shoppingpointsadmin.ui.theme.AppColor
import com.shopping.shoppingpointsadmin.ui.theme.BackgroundColor

@Composable
fun DashBoardScreen(
    navController: NavHostController,
    appViewModel: AppViewModel
) {
    val signOutState = appViewModel.signOut.value
    val context = LocalContext.current

    LaunchedEffect(key1 = signOutState) {
        if (signOutState.error.isNotEmpty()) {
            Toast.makeText(context, signOutState.error, Toast.LENGTH_SHORT)
                .show()
        }

        if (signOutState.success != null) {
            navController.navigate(AdminSubNavigation.AdminLoginNav) {
                popUpTo(AdminSubNavigation.AdminHomeMainNav) {
                    inclusive = true
                }
            }
            val currentBackStack = navController.currentBackStackEntry?.lifecycle?.currentState
            Log.d("SCREEN", "DashBoardScreen: $currentBackStack")
        }
    }

    val currentAdmin = appViewModel.currentUser.value
    Log.d("SCREEN", "DashBoardScreen: ${currentAdmin?.uid.toString()}")
    if (currentAdmin?.uid?.isNotEmpty() == true) {
        appViewModel.getAdminDetails(currentAdmin.uid)
    }
    val adminDetails = appViewModel.getAdminResponse.value

    if (adminDetails.admin != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = BackgroundColor
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row {
                Text(text = "Name : ", fontSize = 20.sp)
                Text(text = adminDetails.admin.name.toString(), fontSize = 20.sp)
            }


            Row {
                Text(text = "Email : ", fontSize = 20.sp)
                Text(text = adminDetails.admin.email.toString(), fontSize = 20.sp)
            }

            Text(text = "DashBoard Screen", fontSize = 20.sp)

            if (signOutState.isLoading) {
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onClick = {
                        appViewModel.signOut()


                    },
                    colors = ButtonDefaults.buttonColors(AppColor)
                ) {
                    Text(
                        text = "SignOut",
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
        }

    }
}