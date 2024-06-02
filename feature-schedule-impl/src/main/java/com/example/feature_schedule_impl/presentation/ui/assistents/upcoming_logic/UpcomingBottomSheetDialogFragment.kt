package com.example.feature_schedule_impl.presentation.ui.assistents.upcoming_logic

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.feature_schedule_api.di.ScheduleFeatureApi
import com.example.feature_schedule_impl.R
import com.example.feature_schedule_impl.databinding.FragmentUpcomingBottomBinding
import com.example.feature_schedule_impl.di.ScheduleFeatureComponent
import com.example.feature_schedule_impl.presentation.model.running.RunningMatchUiModel
import com.example.feature_schedule_impl.presentation.model.upcoming.UpcomingMatchUiModel
import com.example.feature_schedule_impl.presentation.ui.schedule.ScheduleFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nefrit.common.base.BaseBottomSheetDialogFragment
import com.nefrit.common.di.FeatureUtils
import javax.inject.Inject

class UpcomingBottomSheetDialogFragment() :
    BaseBottomSheetDialogFragment(R.layout.fragment_upcoming_bottom) {
    @Inject
    lateinit var vmFactory: UpcomingBottomSheetDialogAssistedViewModel.Factory
    private val viewModel: UpcomingBottomSheetDialogAssistedViewModel by viewModels {
        UpcomingBottomSheetDialogAssistedViewModel.provideFactory(
            vmFactory, assistedParam = arguments?.getParcelable<UpcomingMatchUiModel>(UPCOMING_BOTTOM_TAG)!!
        )
    }
    private val viewBinding:FragmentUpcomingBottomBinding by viewBinding(FragmentUpcomingBottomBinding::bind)
    override fun inject() {
        FeatureUtils.getFeature<ScheduleFeatureComponent>(this, ScheduleFeatureApi::class.java)
            .upcomingBottomComponentFactory().create(this).inject(this)
    }
    override fun initViews() {
        onButtonClickListener()
    }
    fun onButtonClickListener(){
        with(viewBinding){
            notificationBtn.setOnClickListener {
                viewModel.notificationButtonClick(requireContext(),requireActivity())
            }
        }
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
        const val UPCOMING_BOTTOM_TAG = "UPCOMING_BOTTOM_TAG"
        fun newInstance(game: UpcomingMatchUiModel) = ScheduleFragment().apply {
            arguments = Bundle().apply { putParcelable(UPCOMING_BOTTOM_TAG, game) }
        }
    }
}