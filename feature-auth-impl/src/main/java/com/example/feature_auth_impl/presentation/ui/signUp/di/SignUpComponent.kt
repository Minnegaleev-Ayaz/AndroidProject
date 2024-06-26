package com.example.feature_auth_impl.presentation.ui.signUp.di

import androidx.fragment.app.Fragment
import com.example.feature_auth_impl.presentation.ui.signUp.SignUpFragment
import com.nefrit.common.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [SignUpModule::class,
        ViewModelModule::class]
)
interface SignUpComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment
        ): SignUpComponent
    }

    fun inject(fragment: SignUpFragment)
}