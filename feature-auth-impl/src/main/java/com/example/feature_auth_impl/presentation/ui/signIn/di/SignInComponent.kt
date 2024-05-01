package com.example.feature_auth_impl.presentation.ui.signIn.di

import androidx.fragment.app.Fragment
import com.example.feature_auth_impl.presentation.ui.signIn.SignInFragment
import com.example.feature_auth_impl.presentation.ui.signUp.SignUpFragment
import com.example.feature_auth_impl.presentation.ui.signUp.di.SignUpModule
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [SignInModule::class]
)
interface SignInComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment
        ):SignInComponent
    }
    fun inject(fragment: SignInFragment)
}