package com.example.feature_search_impl.presentation.ui.team

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.feature_search_api.di.SearchFeatureApi
import com.example.feature_search_impl.R
import com.example.feature_search_impl.databinding.FragmentDetailedTeamBinding
import com.example.feature_search_impl.di.SearchFeatureComponent
import com.example.feature_search_impl.presentation.adapter.PlayerUiAdapter
import com.example.feature_search_impl.presentation.model.player.PlayerUiModel
import com.example.feature_search_impl.presentation.model.team.PlayerUi
import com.example.feature_search_impl.presentation.model.team.TeamUiModel
import com.nefrit.common.base.BaseFragment
import com.nefrit.common.core.resources.ResourceManager
import com.nefrit.common.di.FeatureUtils
import javax.inject.Inject

class TeamFragment: BaseFragment(R.layout.fragment_detailed_team) {
    @Inject
    lateinit var resourceManager: ResourceManager
    private val viewBinding: FragmentDetailedTeamBinding by viewBinding(
        FragmentDetailedTeamBinding::bind)
    @Inject
    lateinit var vmFactory: TeamAssistedViewModel.Factory

    private val viewModel: TeamAssistedViewModel by viewModels {
        TeamAssistedViewModel.provideFactory(
            vmFactory, assistedParam = arguments?.getParcelable<TeamUiModel>(DETAILDED_TEAM_KEY)!!
        )
    }
    private var rvPlayerAdapter:PlayerUiAdapter?=null

    override fun initViews() {
        val teamUiModel = arguments?.getParcelable<TeamUiModel>(DETAILDED_TEAM_KEY)
        setModelView(teamUiModel)
        with(viewBinding){
            val layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            playerUiRv.layoutManager = layoutManager
            rvPlayerAdapter = PlayerUiAdapter(
                resourceManager = resourceManager,
                glide =  Glide.with(this@TeamFragment),
                onPLayerClicked = {model ->
                    viewModel.openPlayer(model)
                }
            )
            rvPlayerAdapter?.items = teamUiModel?.players as MutableList<PlayerUi>
            playerUiRv.adapter = rvPlayerAdapter
        }
    }

    override fun inject() {
        FeatureUtils.getFeature<SearchFeatureComponent>(this, SearchFeatureApi::class.java)
            .teamComponentFactory().create(this).inject(this)
    }
    fun setModelView(team: TeamUiModel?){
        with(viewBinding){
            if (team!=null){
                if (team.imageUrl!=null){
                    Glide.with(requireContext()).load(team.imageUrl).into(teamImageIv)
                }else{
                    teamImageIv.setImageResource(R.drawable.team_icon)
                }
                Glide.with(requireContext()).load("https://flagcdn.com/64x48/"+team.location?.toLowerCase()+".png").into(locationIv)
                teamNameTv.setText(team.name)

            }
        }
    }
    companion object{
        const val DETAILDED_TEAM_KEY = "DETAILDED_TEAM_KEY"
        fun newInstance(player: PlayerUiModel) = TeamFragment().apply {
            arguments = Bundle().apply { putParcelable(DETAILDED_TEAM_KEY, player) }
        }
    }

}