package com.example.job_app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.job_app.feature_application.ui.ApplicationScreen
import com.example.job_app.feature_application_form.ui.ApplicationFormScreen
import com.example.job_app.feature_auth.ui.LoginScreen
import com.example.job_app.feature_auth.ui.RegisterScreen
import com.example.job_app.feature_feedback.ui.RequestScreen
import com.example.job_app.feature_feedback.ui.ResponseScreen
import com.example.job_app.feature_home.ui.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") },
                navigateToRegisterScreen = { navController.navigate("register") })
        }
        composable("register") {
            RegisterScreen(onRegisterSuccess = { navController.navigate("home") },
                navigateToLoginScreen = { navController.navigate("login") })
        }
        composable("home") {
            HomeScreen(navigateToLoginScreen = { navController.navigate("login") },
                userIsNotAuthorized = {navController.navigate("login")},
                navigateToAddJobApplicationScreen = { navController.navigate("add") },
                navController = navController
            )
        }
        composable("add") {
            ApplicationFormScreen(navigateToLoginScreen = { navController.navigate("login") },
                userIsNotAuthorized = {navController.navigate("login")},
                navigateBack = { navController.popBackStack() },
                navController = navController)
        }
        composable("application/{id}") {
            val id = navController.currentBackStackEntry?.arguments?.getString("id") ?: "No id"
            ApplicationScreen(
                id = id,
                userIsNotAuthorized = {navController.navigate("login")},
                navigateToLoginScreen = { navController.navigate("login") },
                navigateBack = { navController.popBackStack() }
            )
        }
        composable("request") {
            RequestScreen(navController)
        }
        composable("response/{responseMessage}") { backStackEntry ->
            val responseMessage = backStackEntry.arguments?.getString("responseMessage") ?: ""
            ResponseScreen(responseMessage, navController)
        }
    }
}