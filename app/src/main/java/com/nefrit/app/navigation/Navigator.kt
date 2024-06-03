package com.nefrit.app.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.feature_auth_impl.UsersAuthRouter
import com.example.feature_predict_impl.PredictionFeatureRouter
import com.example.feature_predict_impl.presentation.model.MatchPresentationModel
import com.example.feature_predict_impl.presentation.ui.prediction.bottom.PredictionBottomSheetDialogFragment
import com.example.feature_profile_impl.ProfileFeatureRouter
import com.example.feature_schedule_impl.ScheduleFeatureRouter
import com.example.feature_schedule_impl.presentation.model.running.RunningMatchUiModel
import com.example.feature_schedule_impl.presentation.model.upcoming.UpcomingMatchUiModel
import com.example.feature_schedule_impl.presentation.ui.assistents.streams.StreamsBottomSheetDialogFragment
import com.example.feature_schedule_impl.presentation.ui.assistents.upcoming_logic.UpcomingBottomSheetDialogFragment
import com.example.feature_search_impl.SearchFeatureRouter
import com.example.feature_search_impl.presentation.model.player.PlayerUiModel
import com.example.feature_search_impl.presentation.model.team.PlayerUi
import com.example.feature_search_impl.presentation.model.team.TeamUiModel
import com.example.feature_search_impl.presentation.ui.player.PlayerFragment
import com.example.feature_search_impl.presentation.ui.player.PlayerFromTeamFragment
import com.example.feature_search_impl.presentation.ui.team.TeamFragment
import com.example.feature_splash_screen_impl.SplashScreenRouter
import com.nefrit.app.R

class Navigator : UsersAuthRouter, PredictionFeatureRouter, SplashScreenRouter,
    ScheduleFeatureRouter,
    SearchFeatureRouter,ProfileFeatureRouter{

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

    override fun openSignUpFromSignIn() {
        navController?.navigate(R.id.action_signInFragment_to_signUpFragment)
    }

    override fun openSignInFromSignUp() {
        navController?.navigate(R.id.action_signUpFragment_to_signInFragment)
    }

    override fun openPredictionFromSignIn() {
        navController?.navigate(R.id.action_signInFragment_to_predictionFragment)
    }

    override fun openPredictionFromSignUp() {
        navController?.navigate(R.id.action_signUpFragment_to_predictionFragment)
    }

    override fun openSignUpFromSplashScreen() {
        navController?.navigate(R.id.action_splashScreenFragment_to_signUpFragment)
    }

    override fun openPredictionFromSplashScreen() {
        navController?.navigate(R.id.action_splashScreenFragment_to_predictionFragment)
    }

    override fun openPlayerFromSearch(model:PlayerUiModel) {

        navController?.navigate(R.id.action_searchFragment_to_playerFragment, bundleOf(
            PlayerFragment.DETAILDED_PLAYER_KEY to model))
    }

    override fun openTeamFromSearch(model: TeamUiModel) {
        navController?.navigate(R.id.action_searchFragment_to_teamFragment, bundleOf(TeamFragment.DETAILDED_TEAM_KEY to model))
    }

    override fun openPlayerFromTeam(model: PlayerUi) {
        navController?.navigate(R.id.action_teamFragment_to_playerFromTeamFragment, bundleOf(PlayerFromTeamFragment.DETAILDED_PLAYER_FROM_TEAM_KEY to model))
    }

    override fun openStreamsFromRunning(model: RunningMatchUiModel) {
        navController?.navigate(R.id.action_viewPagerFragment_to_streamsBottomSheetDialogFragment,
            bundleOf(StreamsBottomSheetDialogFragment.STREAMS_TAG to model)
        )
    }

    override fun openUpcomingBottom(model: UpcomingMatchUiModel) {
        navController?.navigate(R.id.action_viewPagerFragment_to_upcomingBottomSheetDialogFragment,
            bundleOf(UpcomingBottomSheetDialogFragment.UPCOMING_BOTTOM_TAG to model)
            )
    }

    override fun openSignInFromProfile() {
        navController?.navigate(R.id.action_profileFragment_to_signInFragment)
    }

    override fun openBottomFromMain(match: MatchPresentationModel) {
        navController?.navigate(R.id.action_predictionFragment_to_predictionBottomSheetDialogFragment, bundleOf(
            PredictionBottomSheetDialogFragment.PREDICT_BOTTOM_TAG to match
        ))
    }

    override fun openPredFromUpcoming(model: UpcomingMatchUiModel) {
        navController?.navigate(R.id.action_upcomingBottomSheetDialogFragment_to_predictionBottomSheetDialogFragment,
            bundleOf(PredictionBottomSheetDialogFragment.PREDICT_BOTTOM_TAG to MatchPresentationModel(
                id = model.id,
                firstTeamImage = model.firstTeamImage,
                firstTeamName = model.firstTeamName,
                firstTeamId = model.firstTeamId,
                secondTeamName = model.secondTeamName,
                secondTeamImage = model.secondTeamImage,
                secondTeamId = model.firstTeamId,
                matchTime = model.matchTime,
                matchType = model.matchType?.toInt(),
                leagueName = model.leagueName
            ))
        )
    }
}