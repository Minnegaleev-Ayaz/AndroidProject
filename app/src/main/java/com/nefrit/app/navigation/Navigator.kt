package com.nefrit.app.navigation

import androidx.navigation.NavController
import com.example.feature_auth_impl.UsersAuthRouter

class Navigator : UsersAuthRouter {

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

    override fun openUserAuth(userId: Int) {
        TODO("Not yet implemented")
    }

    override fun returnToUsersAuth() {
        TODO("Not yet implemented")
    }

}