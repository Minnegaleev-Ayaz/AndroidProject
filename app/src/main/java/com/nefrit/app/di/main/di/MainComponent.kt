package com.nefrit.app.di.main.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.nefrit.app.di.main.MainActivity
import com.nefrit.app.di.main.di.DaggerMainComponent
import com.nefrit.common.di.scope.ScreenScope
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        MainDependencies::class
    ],
    modules = [
        MainModule::class
    ]
)
@ScreenScope
interface MainComponent : MainApi {

    companion object {

        fun init(activity: AppCompatActivity, deps: MainDependencies): MainComponent {
            return DaggerMainComponent.factory().create(activity,activity, deps)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            context: Context,
            @BindsInstance
            activity: AppCompatActivity,
            deps: MainDependencies
        ): MainComponent
    }

    fun inject(mainActivity: MainActivity)
}