package com.shopping.shoppingpointsadmin.presentation_layer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

@Composable
fun App(navController: NavHostController) {

    NavHost(navController = navController, startDestination = AdminSubNavigation.AdminLoginNav) {

        navigation<AdminSubNavigation.AdminLoginNav>(startDestination = AdminRoutes.SignUpScreen) {
            composable<AdminRoutes.SignUpScreen> {

            }
            composable<AdminRoutes.LoginScreen> {

            }
            composable<AdminRoutes.ForgetPasswordScreen> {

            }
            composable<AdminRoutes.ResetPasswordScreen> {

            }
        }

        navigation<AdminSubNavigation.AdminHomeMainNav>(startDestination = AdminRoutes.DashBoardScreen) {
            composable<AdminRoutes.DashBoardScreen> {

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