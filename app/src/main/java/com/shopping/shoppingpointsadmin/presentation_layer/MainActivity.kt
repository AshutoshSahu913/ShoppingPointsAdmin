package com.shopping.shoppingpointsadmin.presentation_layer

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.shopping.shoppingpointsadmin.presentation_layer.navigation.App
import com.shopping.shoppingpointsadmin.ui.theme.BackgroundColor
import com.shopping.shoppingpointsadmin.ui.theme.ShoppingPointsAdminTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var isDataLoaded: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        // Install the splash screen
        val splashScreen = installSplashScreen()

        // Keep the splash screen visible until the data is loaded
        splashScreen.setKeepOnScreenCondition {
            !isDataLoaded
        }

        // Simulate loading data asynchronously
        loadData()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingPointsAdminTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(
                                BackgroundColor
                            )
                    ) {
                        App(navController = navController)
                    }
                }
            }
        }
    }

    // Simulate an async data loading process
    private fun loadData() {
        // Simulating a delay or data fetching process using a thread or coroutines
        // Here you would load your data (e.g. from Firestore, a local database, etc.)
        Thread {
            // Simulate a network or database call delay
            Thread.sleep(3000) // 3 seconds delay to simulate loading
            // Once the data is loaded, set `isDataLoaded` to true
            isDataLoaded = true
        }.start()
    }
}
