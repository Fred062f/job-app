package com.example.job_app.navigation

import ProfileScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.job_app.feature_application_form.ui.ApplicationFormScreen
import com.example.job_app.feature_application_form.viewmodel.NotificationScheduler
import com.example.job_app.feature_auth.ui.LoginScreen
import com.example.job_app.feature_auth.ui.RegisterScreen
import com.example.job_app.feature_home.repository.FirestoreRepository
import com.example.job_app.feature_home.ui.HomeScreen
import com.example.job_app.feature_profile.ui.EditProfileScreenPreview
import com.example.job_app.feature_profile.ui.MyDocumentsScreen


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
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { navController.navigate("home") },
                navigateToLoginScreen = { navController.navigate("login") }
            )
        }
        composable("home") {
            HomeScreen(
                navigateToLoginScreen = { navController.navigate("login") },
                userIsNotAuthorized = { navController.navigate("login") },
                navigateToAddJobApplicationScreen = { navController.navigate("add") }
            )
        }
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
        composable("add") {
            ApplicationFormScreen(
                navigateToLoginScreen = { navController.navigate("login") },
                userIsNotAuthorized = { navController.navigate("login") },
                navigateBack = { navController.popBackStack() },
                firestoreRepository = FirestoreRepository(),
                notificationScheduler = NotificationScheduler(LocalContext.current),
                )
        }
    }
}
