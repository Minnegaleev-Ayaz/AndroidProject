package com.nefrit.app.di.app

import androidx.room.RoomDatabase
import com.example.feature_predict_impl.data.di.PredictNetworkModule
import com.example.firebase_api.di.FirebaseModule
import com.example.room_api.RoomDatabaseModule
import com.nefrit.app.App
import com.nefrit.app.di.deps.ComponentDependenciesModule
import com.nefrit.app.di.deps.ComponentHolderModule
import com.nefrit.app.di.main.di.MainDependencies
import com.nefrit.common.di.CommonApi
import com.nefrit.common.di.modules.CommonModule
import com.nefrit.common.di.modules.NetworkModule
import com.nefrit.common.di.modules.PreferencesModule
import com.nefrit.common.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        AppModule::class,
        CommonModule::class,
        NetworkModule::class,
        PredictNetworkModule::class,
        PreferencesModule::class,
        NavigationModule::class,
        ComponentHolderModule::class,
        ComponentDependenciesModule::class,
        FeatureManagerModule::class,
        FirebaseModule::class,
        RoomDatabaseModule::class,
    ],
    dependencies = []
)
interface AppComponent : MainDependencies, CommonApi {

    companion object {

        fun init(application: App): AppComponent {
            return DaggerAppComponent
                .builder()
                .application(application)
                .build()
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}