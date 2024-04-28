package com.example.job_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.job_app.feature_application_form.ui.ApplicationFormScreen
import com.example.job_app.feature_auth.ui.LoginScreen
import com.example.job_app.feature_auth.ui.RegisterScreen
import com.example.job_app.feature_home.ui.ApplicationInfoScreen
import com.example.job_app.feature_home.ui.HomeScreen
import com.example.job_app.feature_profile.ui.ProfileScreen
import com.example.job_app.feature_profile.ui.EditProfileScreenPreview
import com.example.job_app.feature_profile.ui.EditScreen
import com.example.job_app.feature_profile.ui.MyDocumentsScreen
import com.example.job_app.feature_profile.ui.ProfileScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("application") },
                navigateToRegisterScreen = { navController.navigate("register") })
        }
        composable("register") {
            RegisterScreen(onRegisterSuccess = { navController.navigate("application") },
                navigateToLoginScreen = { navController.navigate("login") })
        }
        composable("home") {
            HomeScreen(navigateToProfileScreen = { navController.navigate("profile") },
                navigateToApplicationInfoScreen = { navController.navigate("applicationInfo") },
                userIsNotAuthorized = {navController.navigate("login")},
                navController = navController)
        }

        composable("applicationInfo/{item}") { navBackStackEntry ->
            val item = navBackStackEntry.arguments?.getString("item")
            item?.let {
                ApplicationInfoScreen(item=it) { navController.navigate("profile") }
            }
        }
        composable("editprofile") {
            EditProfileScreenPreview()
        }
        composable("mydocuments") {
           MyDocumentsScreen()
        }
        composable("application") { ApplicationFormScreen()}


    }
}
