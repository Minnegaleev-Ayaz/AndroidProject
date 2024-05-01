package com.nefrit.app.navigation

import androidx.navigation.NavController
import com.example.feature_auth_impl.UsersAuthRouter
import com.example.feature_predict_impl.PredictionFeatureRouter
import com.example.feature_splash_screen_impl.SplashScreenRouter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nefrit.app.R

class Navigator : UsersAuthRouter,PredictionFeatureRouter,SplashScreenRouter {

    private var navController: NavController? = null

    fun attachNavController(navController: NavController, graph: Int) {
        navController.setGraph(graph)
        this.navController = navController
    }

    fun detachNavController(navController: NavController) {
        if (this.navController == navController) {
            this.navController = null
        }
    }
    override fun openSignUpFromSignIn() {
        navController?.navigate(R.id.action_signInFragment_to_signUpFragment)
    }

    override fun openSignInFromSignUp() {
        navController?.navigate(R.id.action_signUpFragment_to_signInFragment)
    }

    override fun openPredictionFromSignIn() {
        navController?.navigate(R.id.action_signInFragment_to_predictionFragment)
    }
    override fun openPredictionFromSignUp() {
        navController?.navigate(R.id.action_signUpFragment_to_predictionFragment)
    }

    override fun openSignUpFromSplashScreen() {
        navController?.navigate(R.id.action_splashScreenFragment_to_signUpFragment)
    }

    override fun openPredictionFromSplashScreen() {
        navController?.navigate(R.id.action_splashScreenFragment_to_predictionFragment)
    }

}