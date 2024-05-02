package com.example.job_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.job_app.feature_application_form.ui.ApplicationFormScreen
import androidx.navigation.compose.rememberNavController
import com.example.job_app.feature_auth.ui.LoginScreen
import com.example.job_app.feature_auth.ui.RegisterScreen
import com.example.job_app.feature_home.ui.HomeScreen
import com.example.job_app.feature_profile.ui.ProfileScreen
import com.example.job_app.feature_profile.ui.EditProfileScreenPreview
import com.example.job_app.feature_profile.ui.EditScreen
import com.example.job_app.feature_profile.ui.MyDocumentsScreen
import com.example.job_app.feature_profile.ui.ProfileScreen

@Composable
fun ApplicationInfoScreen(item: String, content: () -> Unit) {

}

@Composable
fun Navigation() {
    val navCondtroller = rememberNavController()
    NavHost(navController = navController, startDestination = "application") {
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

        composable("applicationInfo/{item}") { navBackStackEntry ->
            val item = navBackStackEntry.arguments?.getString("item")
            item?.let {
                
                ApplicationInfoScreen(item=it) { navController.navigate("profile") }
            }
        }

        composable("profile") {
            ProfileScreen(navController = navController)
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
