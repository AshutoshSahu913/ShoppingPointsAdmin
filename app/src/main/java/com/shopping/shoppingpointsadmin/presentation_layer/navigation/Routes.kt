package com.shopping.shoppingpointsadmin.presentation_layer.navigation

import kotlinx.serialization.Serializable

sealed class AdminSubNavigation {

    @Serializable
    data object AdminLoginNav : AdminSubNavigation()

    @Serializable
    data object AdminHomeMainNav : AdminSubNavigation()
}

sealed class AdminRoutes {


    @Serializable
    object LoginScreen

    @Serializable
    object SignUpScreen

    @Serializable
    object ForgetPasswordScreen

    @Serializable
    object ResetPasswordScreen

    @Serializable
    object OnboardingScreen

    @Serializable
    object DashBoardScreen

    @Serializable
    object AddProductsScreen

    @Serializable
    object NotificationScreen

    @Serializable
    object CategoryScreen

    @Serializable
    object AdminProfile
    @Serializable
    object OrderScreen
}