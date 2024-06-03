package com.example.feature_search_impl.presentation.ui.player

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.feature_search_api.di.SearchFeatureApi
import com.example.feature_search_impl.R
import com.example.feature_search_impl.databinding.FragmentDetailedPlayerBinding
import com.example.feature_search_impl.di.SearchFeatureComponent
import com.example.feature_search_impl.presentation.model.player.PlayerUiModel
import com.nefrit.common.base.BaseFragment
import com.nefrit.common.di.FeatureUtils
import javax.inject.Inject

class PlayerFragment:BaseFragment(R.layout.fragment_detailed_player) {
    private val viewBinding:FragmentDetailedPlayerBinding by viewBinding(FragmentDetailedPlayerBinding::bind)
    @Inject
    lateinit var vmFactory: PlayerAssistedViewModel.Factory

    private val viewModel: PlayerAssistedViewModel by viewModels {
        PlayerAssistedViewModel.provideFactory(
            vmFactory, assistedParam = arguments?.getParcelable<PlayerUiModel>(DETAILDED_PLAYER_KEY)!!
        )
    }

    override fun initViews() {
        val playerUiModel = arguments?.getParcelable<PlayerUiModel>(DETAILDED_PLAYER_KEY)
        setModelView(playerUiModel)
    }

    override fun inject() {
        FeatureUtils.getFeature<SearchFeatureComponent>(this, SearchFeatureApi::class.java)
            .playerComponentFactory().create(this).inject(this)
    }
    fun setModelView(player: PlayerUiModel?){

        with(viewBinding){
            ageRl.visibility = View.GONE
            teamLayoutRl.visibility = View.GONE
            if (player!=null){
                if (player.imageUrl!=null){
                    Glide.with(requireContext()).load(player.imageUrl).into(playerImageIv)
                }else{
                    playerImageIv.setImageResource(R.drawable.user_icon)
                }
                Glide.with(requireContext()).load("https://flagcdn.com/64x48/"+player.nationality?.toLowerCase()+".png").into(nationlityIv)
                playerNameTv.text = player.name
                playerRoleTv.text = player.role

                firstLastNameTv.text = getString(R.string.first_last_name,player.firstName.toString(),player.lastName.toString())
                if (player.active!!){
                    teamLayoutRl.visibility = View.VISIBLE
                    teamNameTv.setText(player.teamName)
                    if (player.teamImageUrl!=null){
                        Glide.with(requireContext()).load(player.teamImageUrl).into(teamImageIv)
                    }else{
                        playerImageIv.setImageResource(R.drawable.team_icon)
                    }
                    unactiveTv.visibility=View.INVISIBLE
                }else{
                    teamLayoutRl.visibility = View.GONE
                    unactiveTv.text="Unactive player"
                }
            }
        }
    }
    companion object{
        const val DETAILDED_PLAYER_KEY = "DETAILDED_PLAYER_KEY"
    }

}