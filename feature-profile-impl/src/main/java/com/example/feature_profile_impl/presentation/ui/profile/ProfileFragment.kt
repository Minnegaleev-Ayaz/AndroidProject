package com.example.feature_profile_impl.presentation.ui.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.feature_profile_api.di.ProfileFeatureApi
import com.example.feature_profile_impl.R
import com.example.feature_profile_impl.databinding.FragmentProfileBinding
import com.example.feature_profile_impl.di.ProfileFeatureCompomemt
import com.nefrit.common.base.BaseFragment
import com.nefrit.common.di.FeatureUtils
import com.nefrit.common.utils.AsyncResult
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    @Inject
    lateinit var factory: Lazy<ViewModelProvider.Factory>
    private val viewModel: ProfileViewModel by viewModels { factory.get() }
    private val viewBinding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)

    override fun initViews() {
        viewModel.subscribe()
        lifecycleScope.launch {
            subscribe(viewModel)
        }
        subscribeQuitButton()
    }

    override fun inject() {
        FeatureUtils.getFeature<ProfileFeatureCompomemt>(this, ProfileFeatureApi::class.java)
            .profileComponentFactory().create(this).inject(this)
    }
    fun subscribeQuitButton(){
        with(viewBinding){
            quitBtn.setOnClickListener {
                viewModel.logOutFromAccount()
            }
        }
    }
    suspend fun subscribe(viewModel: ProfileViewModel) {
        viewModel.userFlow.collect { result ->
            with(viewBinding) {
                when (result) {
                    is AsyncResult.Success -> {}

                    is AsyncResult.Error -> {}

                    else -> {}
                }
            }

        }
    }
}