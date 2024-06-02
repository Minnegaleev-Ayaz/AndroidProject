package com.example.feature_search_impl.presentation.ui.search

import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.feature_search_api.di.SearchFeatureApi
import com.example.feature_search_impl.R
import com.example.feature_search_impl.databinding.FragmentSearchBinding
import com.example.feature_search_impl.di.SearchFeatureComponent
import com.example.feature_search_impl.presentation.adapter.QuerryAdapter
import com.example.feature_search_impl.presentation.adapter.TeamAndPlayersAdapter
import com.nefrit.common.base.BaseFragment
import com.nefrit.common.core.resources.ResourceManager
import com.nefrit.common.di.FeatureUtils
import com.nefrit.common.utils.AsyncResult
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : BaseFragment(R.layout.fragment_search) {
    @Inject
    lateinit var factory: Lazy<ViewModelProvider.Factory>
    private val viewModel: SearchViewModel by viewModels { factory.get() }
    private val viewBinding: FragmentSearchBinding by viewBinding(FragmentSearchBinding::bind)
    private var rvQuerry: QuerryAdapter? = null
    private var rvTeamAndPlayers: TeamAndPlayersAdapter? = null

    @Inject
    lateinit var resourceManager: ResourceManager

    override fun initViews() {
        viewModel.getQuerries()
        lifecycleScope.launch {
            subscribeQuerry(viewModel)
        }
        lifecycleScope.launch{
            subscribeTeamsAndPlayers(viewModel)
        }
        subscribeETClickListener(viewModel)
        subscribeEditText(viewModel)
        with(viewBinding) {
            val layoutManagerQuerry =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            querriesRv.layoutManager = layoutManagerQuerry
            rvQuerry = QuerryAdapter(
                onRootClicked = {model->
                    with(viewBinding){
                        viewModel.searching(model.text)
                        serchingEt.setText(model.text)
                    }
                },
                onCrossClicked = {model->
                    viewModel.deleteQuerry(model)
                    viewModel.getQuerries()
                },
            )
            querriesRv.adapter = rvQuerry
            val layoutManagerResponse =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            responseRv.layoutManager = layoutManagerResponse
            rvTeamAndPlayers = TeamAndPlayersAdapter(
                glide = Glide.with(this@SearchFragment),
                onTeamClicked = {model->
                    viewModel.openTeam(model)
                },
                onPlayerClicked = {model ->
                    Log.e("Ayaz","search" +model.toString())
                    viewModel.openPlayer(model)
                },
                resourceManager = resourceManager
            )
            responseRv.adapter = rvTeamAndPlayers
        }
        viewBinding.serchingEt.requestFocus()
    }

    override fun inject() {
        FeatureUtils.getFeature<SearchFeatureComponent>(this, SearchFeatureApi::class.java)
            .searchComponentFactory().create(this).inject(this)
    }

    suspend private fun subscribeQuerry(viewModel: SearchViewModel) {
        with(viewBinding) {
            viewModel.getQuerryMatchesFlow.collect { result ->
                when (result) {
                    is AsyncResult.Success -> {
                        rvQuerry?.updateItems(result.getDataOrNull()!!)
                        Log.e("Ayaz", rvQuerry?.items.toString())
                        querriesRv.adapter = rvQuerry
                    }

                    is AsyncResult.Error -> {
                        viewModel.errorHandlling(result.getExceptionOrNull()!!)
                    }

                    else -> {

                    }
                }
            }
        }
    }

    fun subscribeEditText(viewModel: SearchViewModel) {
        with(viewBinding) {
            serchingEt.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.refresh()
                    viewModel.saveQuerry(serchingEt.text.toString())
                    viewModel.searching(querry_text = serchingEt.text.toString())
                    true
                } else {
                    false
                }
            }
        }
    }

    suspend fun subscribeTeamsAndPlayers(viewModel: SearchViewModel) {
        with(viewBinding) {
            viewModel.searchFlow.collect { result ->
                when (result) {
                    is AsyncResult.Success -> {
                        Log.e("Ayaz","enter_querry")
                        querriesRv.visibility = View.GONE
                        responseRv.visibility = View.VISIBLE
                        result.getDataOrNull()?.let { rvTeamAndPlayers?.updateItems(it) }
                    }

                    is AsyncResult.Error -> {
                        viewModel.errorHandlling(result.getExceptionOrNull()!!)
                    }

                    else -> {

                    }
                }

            }
        }
    }

    fun subscribeETClickListener(viewModel: SearchViewModel) {
        with(viewBinding) {
            serchingEt.setOnClickListener {
                responseRv.visibility = View.GONE
                querriesRv.visibility = View.VISIBLE
                viewModel.getQuerries()
            }
        }
    }

}