package com.nefrit.app.di.main.di

import com.nefrit.app.di.deps.ComponentDependencies
import com.nefrit.app.navigation.Navigator

interface MainDependencies : ComponentDependencies {

    fun navigator(): Navigator
}