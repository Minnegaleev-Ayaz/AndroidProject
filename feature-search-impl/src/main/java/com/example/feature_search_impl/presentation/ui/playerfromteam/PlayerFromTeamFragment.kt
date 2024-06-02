package com.example.feature_search_impl.presentation.ui.player

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.feature_search_api.di.SearchFeatureApi
import com.example.feature_search_impl.R
import com.example.feature_search_impl.databinding.FragmentDetailedPlayerBinding
import com.example.feature_search_impl.di.SearchFeatureComponent
import com.example.feature_search_impl.presentation.model.player.PlayerUiModel
import com.example.feature_search_impl.presentation.model.team.PlayerUi
import com.nefrit.common.base.BaseFragment
import com.nefrit.common.di.FeatureUtils
import javax.inject.Inject

class PlayerFromTeamFragment : BaseFragment(R.layout.fragment_detailed_player) {
    private val viewBinding: FragmentDetailedPlayerBinding by viewBinding(
        FragmentDetailedPlayerBinding::bind
    )

    @Inject
    lateinit var vmFactory: PlayerFromTeamAssistedViewModel.Factory

    private val viewModel: PlayerFromTeamAssistedViewModel by viewModels {
        PlayerFromTeamAssistedViewModel.provideFactory(
            vmFactory,
            assistedParam = arguments?.getParcelable<PlayerUi>(DETAILDED_PLAYER_FROM_TEAM_KEY)!!
        )
    }

    override fun initViews() {
        val playerUi = arguments?.getParcelable<PlayerUi>(DETAILDED_PLAYER_FROM_TEAM_KEY)
        setModelView(playerUi)
    }

    override fun inject() {
        FeatureUtils.getFeature<SearchFeatureComponent>(this, SearchFeatureApi::class.java)
            .playerFromTeamComponentFactory().create(this).inject(this)
    }

    fun setModelView(player: PlayerUi?) {
        with(viewBinding) {
            if (player != null) {
                if (player.imageUrl != null) {
                    Glide.with(requireContext()).load(player.imageUrl).into(playerImageIv)
                } else {
                    playerImageIv.setImageResource(R.drawable.user_icon)
                }
                Glide.with(requireContext()).load("https://flagcdn.com/64x48/"+player.nationality?.toLowerCase()+".png").into(nationlityIv)
                teamLayoutRl.visibility = View.GONE
                ageTitleTv.text = player.age.toString()
                playerNameTv.text = player.name
                playerRoleTv.text = player.role
                firstLastNameTv.text = getString(
                    R.string.first_last_name,
                    player.firstName.toString(),
                    player.lastName.toString()
                )
            }
        }
    }

    companion object {
        const val DETAILDED_PLAYER_FROM_TEAM_KEY = "DETAILDED_PLAYER__FROM_TEAM_KEY"
    }

}