package com.example.job_app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ProfileScreen
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.job_app.feature_application.ui.ApplicationScreen
import com.example.job_app.feature_application_form.ui.ApplicationFormScreen
import com.example.job_app.feature_application_form.viewmodel.NotificationScheduler
import com.example.job_app.feature_auth.ui.LoginScreen
import com.example.job_app.feature_auth.ui.RegisterScreen
import com.example.job_app.feature_feedback.ui.RequestScreen
import com.example.job_app.feature_feedback.ui.ResponseScreen
import com.example.job_app.feature_home.repository.FirestoreRepository
import com.example.job_app.feature_home.ui.HomeScreen
import com.example.job_app.feature_profile.ui.EditProfileScreenPreview
import com.example.job_app.feature_profile.ui.MyDocumentsScreen

// Victor
@Composable
fun ApplicationInfoScreen(item: String, content: () -> Unit) {

}
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") },
                navigateToRegisterScreen = { navController.navigate("register") }
            )
        }
// Victor
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { navController.navigate("home") },
                navigateToLoginScreen = { navController.navigate("login") }
            )
        }
// Frederik
        composable("home") {
            HomeScreen(
                navigateToLoginScreen = { navController.navigate("login") },
                userIsNotAuthorized = {navController.navigate("login")},
                navigateToAddJobApplicationScreen = { navController.navigate("add") },
                navController = navController
            )
        }
// Victor
        composable("profile") {
            ProfileScreen(navController = navController)
        }
        composable("applicationInfo/{item}") { navBackStackEntry ->
            val item = navBackStackEntry.arguments?.getString("item")
            item?.let {
                ApplicationInfoScreen(item = it) { navController.navigate("profile") }
            }
        }
        composable("editprofile") {
            EditProfileScreenPreview()
        }
        composable("mydocuments") {
            MyDocumentsScreen()
        }
// Frederik
        composable("add") {
            ApplicationFormScreen(
                navigateToLoginScreen = { navController.navigate("login") },
                userIsNotAuthorized = { navController.navigate("login") },
                navigateBack = { navController.popBackStack() },
                navController = navController,
            )
        }
        composable("application/{id}") {
            val id = navController.currentBackStackEntry?.arguments?.getString("id") ?: "No id"
            ApplicationScreen(
                id = id,
                userIsNotAuthorized = { navController.navigate("login") },
                navigateToLoginScreen = { navController.navigate("login") },
                navigateBack = { navController.popBackStack() },
                navController = navController
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
