package com.example.feature_schedule_impl.presentation.ui.assistents.streams

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.feature_schedule_api.di.ScheduleFeatureApi
import com.example.feature_schedule_impl.R
import com.example.feature_schedule_impl.databinding.FragmentStreamsBinding
import com.example.feature_schedule_impl.di.ScheduleFeatureComponent
import com.example.feature_schedule_impl.presentation.adapter.StreamsAdapter
import com.example.feature_schedule_impl.presentation.model.running.RunningMatchUiModel
import com.example.feature_schedule_impl.presentation.model.running.RunningStreamUiModel
import com.example.feature_schedule_impl.presentation.ui.assistents.upcoming_logic.UpcomingBottomSheetDialogFragment
import com.example.feature_schedule_impl.presentation.ui.schedule.ScheduleFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nefrit.common.base.BaseBottomSheetDialogFragment
import com.nefrit.common.di.FeatureUtils
import javax.inject.Inject

class StreamsBottomSheetDialogFragment() :
    BaseBottomSheetDialogFragment(R.layout.fragment_streams) {
    @Inject
    lateinit var vmFactory: StreamsBottomSheetDialogAssistedViewModel.Factory
    private val viewModel: StreamsBottomSheetDialogAssistedViewModel by viewModels {
        StreamsBottomSheetDialogAssistedViewModel.provideFactory(
            vmFactory, assistedParam = arguments?.getParcelable<RunningMatchUiModel>(STREAMS_TAG)!!
        )
    }
    private val viewBinding:FragmentStreamsBinding by viewBinding(FragmentStreamsBinding::bind)
    private var rvAdapter: StreamsAdapter? = null

    override fun initViews() {
        val runningMatchUiModel = arguments?.getParcelable<RunningMatchUiModel>(STREAMS_TAG)
        if (runningMatchUiModel != null) {
            setContentView(runningMatchUiModel)
        }
    }
    fun setContentView(runningMatchUiModel: RunningMatchUiModel){
        with(viewBinding){
            val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            streamsRv.layoutManager = layoutManager
            rvAdapter = StreamsAdapter(glide = Glide.with(this@StreamsBottomSheetDialogFragment),)
            rvAdapter?.streamsList = runningMatchUiModel.streams as MutableList<RunningStreamUiModel>
            Log.e("Ayaz",rvAdapter?.streamsList.toString())
            streamsRv.adapter = rvAdapter
        }
    }

    override fun inject() {
        FeatureUtils.getFeature<ScheduleFeatureComponent>(this, ScheduleFeatureApi::class.java)
            .streamComponentFactory().create(this).inject(this)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { dialogInterface ->
            val d = dialogInterface as BottomSheetDialog
            val bottomSheet = d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED // Установите желаемое состояние
        }
        return dialog
    }

    companion object {
        const val STREAMS_TAG = "STREAMS_TAG"
        fun newInstance(game: RunningMatchUiModel) = ScheduleFragment().apply {
            arguments = Bundle().apply { putParcelable(STREAMS_TAG, game) }
        }
    }
}