package com.example.feature_schedule_impl.presentation.ui.schedule

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.feature_predict_impl.presentation.ui.prediction.prediction.ScheduleAssistedViewModel
import com.example.feature_schedule_api.di.ScheduleFeatureApi
import com.example.feature_schedule_impl.R
import com.example.feature_schedule_impl.databinding.FragmentScheduleBinding
import com.example.feature_schedule_impl.di.ScheduleFeatureComponent
import com.example.feature_schedule_impl.presentation.adapter.MatchAdapter
import com.example.feature_schedule_impl.presentation.model.past.PastMatchUiModel
import com.example.feature_schedule_impl.presentation.model.running.RunningMatchUiModel
import com.example.feature_schedule_impl.presentation.model.upcoming.UpcomingMatchUiModel
import com.example.feature_schedule_impl.presentation.view_pager.ViewPagerFragment
import com.nefrit.common.base.BaseFragment
import com.nefrit.common.di.FeatureUtils
import com.nefrit.common.utils.AsyncResult
import com.nefrit.common.utils.ParamsKey
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScheduleFragment : BaseFragment(R.layout.fragment_schedule) {
    private val viewBinding: FragmentScheduleBinding by viewBinding(FragmentScheduleBinding::bind)
    private var rvMatch: MatchAdapter? = null

    @Inject
    lateinit var vmFactory: ScheduleAssistedViewModel.Factory

    private val viewModel: ScheduleAssistedViewModel by viewModels {
        ScheduleAssistedViewModel.provideFactory(
            vmFactory, assistedParam = arguments?.getString(
                MATCH_TYPE
            )!!
        )
    }

    override fun initViews() {
        val matchType = arguments?.getString(MATCH_TYPE)
        viewBinding.titleTv.text = matchType+" matches"
        lifecycleScope.launch {
            subscribe(viewModel, matchType)
        }
        with(viewBinding) {
            val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            matchesRv.layoutManager = layoutManager
            rvMatch = MatchAdapter(
                glide = Glide.with(this@ScheduleFragment),
                onStatsClicked = {it->
                    viewModel.openUpcomingBottomSheetDialog(it)
                },
                onStreamsClicked = {it->
                    viewModel.openStreamsBottomSheetDialog(it)
                }
            )
            matchesRv.adapter = rvMatch
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initialize()
    }
    override fun inject() {
         FeatureUtils.getFeature<ScheduleFeatureComponent>(this, ScheduleFeatureApi::class.java)
            .scheduleComponentFactory().create(this).inject(this)

    }
    suspend fun subscribe(viewModel: ScheduleAssistedViewModel, matchType: String?) {
        with(viewBinding) {
            when (matchType) {
                ViewPagerFragment.past -> {
                    viewModel.pastMatchesFlow.collect { result ->
                        when (result) {
                            is AsyncResult.Loading -> {
                                errorTv.visibility = View.INVISIBLE
                                matchesRv.visibility = View.INVISIBLE
                                loadingPb.visibility = View.VISIBLE
                            }

                            is AsyncResult.Success -> {
                                matchesRv.visibility = View.VISIBLE
                                loadingPb.visibility = View.INVISIBLE
                                rvMatch?.pastList =
                                    result.getDataOrNull() as MutableList<PastMatchUiModel>
                                matchesRv.adapter = rvMatch
                            }

                            is AsyncResult.Error -> {
                                loadingPb.visibility = View.INVISIBLE
                                errorTv.visibility = View.VISIBLE
                                errorTv.text = result.getExceptionOrNull()!!.message
                                viewModel.errorHandlling(result.getExceptionOrNull()!!)
                            }

                            else -> {
                            }
                        }

                    }
                }

                ViewPagerFragment.running -> {
                    viewModel.runningMatchesFlow.collect { result ->
                        when (result) {
                            is AsyncResult.Loading -> {
                                errorTv.visibility = View.INVISIBLE
                                matchesRv.visibility = View.INVISIBLE
                                loadingPb.visibility = View.VISIBLE
                            }

                            is AsyncResult.Success -> {
                                matchesRv.visibility = View.VISIBLE
                                loadingPb.visibility = View.INVISIBLE
                                rvMatch?.runningList =
                                    result.getDataOrNull() as MutableList<RunningMatchUiModel>
                                if (result.getDataOrNull()!!.size==0){
                                    emptyMatchesTv.visibility = View.VISIBLE
                                }
                                matchesRv.adapter = rvMatch
                            }

                            is AsyncResult.Error -> {
                                loadingPb.visibility = View.INVISIBLE
                                errorTv.visibility = View.VISIBLE
                                errorTv.text = result.getExceptionOrNull()!!.message
                                viewModel.errorHandlling(result.getExceptionOrNull()!!)
                            }

                            else -> {
                            }
                        }

                    }
                }

                ViewPagerFragment.upcoming -> {
                    viewModel.upcomingMatchesFlow.collect { result ->
                        when (result) {
                            is AsyncResult.Loading -> {
                                errorTv.visibility = View.INVISIBLE
                                matchesRv.visibility = View.INVISIBLE
                                loadingPb.visibility = View.VISIBLE
                            }

                            is AsyncResult.Success -> {
                                matchesRv.visibility = View.VISIBLE
                                loadingPb.visibility = View.INVISIBLE
                                rvMatch?.upcomingList =
                                    result.getDataOrNull() as MutableList<UpcomingMatchUiModel>
                                matchesRv.adapter = rvMatch
                            }

                            is AsyncResult.Error -> {
                                loadingPb.visibility = View.INVISIBLE
                                errorTv.visibility = View.VISIBLE
                                throw result.getExceptionOrNull()!!
                                /*viewModel.errorHandlling(result.getExceptionOrNull()!!)*/
                            }

                            else -> {
                            }
                        }

                    }
                }

                null -> {
                }

            }
        }
        viewModel.errorFlow.collect {
            it?.message?.let { it1 -> showToast(it1) }
        }
    }


    companion object {
        const val MATCH_TYPE = "MATCH_TYPE"
        fun newInstance(game: String) = ScheduleFragment().apply {
            arguments = Bundle().apply { putString(MATCH_TYPE, game) }
        }
    }
}