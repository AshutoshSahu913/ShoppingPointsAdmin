package com.shopping.shoppingpointsadmin.presentation_layer.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.shopping.shoppingpointsadmin.presentation_layer.screens.authScreens.ForgetPasswordScreen
import com.shopping.shoppingpointsadmin.presentation_layer.screens.authScreens.LoginScreen
import com.shopping.shoppingpointsadmin.presentation_layer.screens.authScreens.SignUpScreen
import com.shopping.shoppingpointsadmin.presentation_layer.screens.homeScreens.DashBoardScreen
import com.shopping.shoppingpointsadmin.presentation_layer.viewModel.AppViewModel

@Composable
fun App(navController: NavHostController) {

    val appViewModel: AppViewModel = hiltViewModel()


    val startScreen = if (Firebase.auth.currentUser == null) {
        AdminSubNavigation.AdminLoginNav
    } else {
        AdminSubNavigation.AdminHomeMainNav
    }

    NavHost(navController = navController, startDestination = startScreen) {

        navigation<AdminSubNavigation.AdminLoginNav>(startDestination = AdminRoutes.SignUpScreen) {

            composable<AdminRoutes.SignUpScreen>( exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(500)
                ) + fadeOut()
            },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    ) + fadeIn()
                },
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(500)
                    )+ fadeIn()
                }, popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    )+ fadeOut()
                }) {
                SignUpScreen(navController, appViewModel)
            }

            composable<AdminRoutes.LoginScreen>( exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(500)
                ) + fadeOut()
            },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    ) + fadeIn()
                },
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(500)
                    )+ fadeIn()
                }, popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    )+ fadeOut()
                }) {
                LoginScreen(navController, appViewModel)
            }

            composable<AdminRoutes.ForgetPasswordScreen> {
                ForgetPasswordScreen(navController, appViewModel)
            }

            composable<AdminRoutes.ResetPasswordScreen> {

            }

        }

        navigation<AdminSubNavigation.AdminHomeMainNav>(startDestination = AdminRoutes.DashBoardScreen) {

            composable<AdminRoutes.DashBoardScreen>(exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(500)
                ) + fadeOut()
            },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    ) + fadeIn()
                },
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(500)
                    ) + fadeIn()
                }, popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(500)
                    ) + fadeOut()
                }) {
                DashBoardScreen(navController, appViewModel)
            }

            composable<AdminRoutes.AddProductsScreen> {

            }

            composable<AdminRoutes.NotificationScreen> {

            }

            composable<AdminRoutes.CategoryScreen> {

            }

            composable<AdminRoutes.AdminProfile> {

            }
        }

        composable<AdminRoutes.OrderScreen> {

        }
    }

}