package com.example.job_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.job_app.feature_auth.ui.LoginScreen
import com.example.job_app.feature_auth.ui.RegisterScreen
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
            HomeScreen(navigateOnSuccess = { navController.navigate("login") },
                userIsNotAuthorized = {navController.navigate("login")})
        }
    }
}