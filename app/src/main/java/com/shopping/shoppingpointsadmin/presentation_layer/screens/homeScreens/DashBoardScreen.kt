package com.shopping.shoppingpointsadmin.presentation_layer.screens.homeScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shopping.shoppingpointsadmin.presentation_layer.viewModel.AppViewModel
import com.shopping.shoppingpointsadmin.ui.theme.BackgroundColor

@Composable
fun DashBoardScreen(navController: NavHostController, appViewModel: AppViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = BackgroundColor
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "DashBoard Screen", fontSize = 20.sp)
    }
}