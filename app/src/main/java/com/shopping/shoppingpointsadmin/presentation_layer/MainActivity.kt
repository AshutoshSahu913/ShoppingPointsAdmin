package com.shopping.shoppingpointsadmin.presentation_layer

import android.os.Bundle
import android.view.View
import android.window.SplashScreenView
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
    override fun onCreate(savedInstanceState: Bundle?) {
// Install the splash screen
        val splashScreen = installSplashScreen()

        // Keep the splash screen visible until some condition is met
        // Set an exit animation listener
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            val splashScreenView: View = splashScreenViewProvider.view

            // Animate the splash screen exit, e.g. fading out
            splashScreenView
                .animate()
                .alpha(0f)  // Fade out effect
                .setDuration(1000L)  // Animation duration (1 second)
                .withEndAction {
                    // Remove the splash screen after the animation
                    splashScreenViewProvider.remove()
                }
                .start()
        }

//        splashScreen.setKeepOnScreenCondition(condition = {
//            someCondition()
//        })

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
}

// This is your condition function to determine when to dismiss the splash screen
private fun someCondition(): Boolean {
    // Return true if the condition to keep the splash screen is met
    // Return false when the splash screen should be dismissed
    // For example, you might wait for some data to load or some initialization
    return isDataLoaded
}

// Simulate a condition (like data loading)
private var isDataLoaded: Boolean = false

// You can update the condition in your lifecycle or based on some logic
private fun loadData() {
    // Simulate data loading
    // When the data is loaded, set `isDataLoaded` to true
    isDataLoaded = true
}