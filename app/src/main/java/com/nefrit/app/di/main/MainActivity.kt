package com.nefrit.app.di.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nefrit.app.R
import com.nefrit.app.databinding.ActivityMainBinding
import com.nefrit.app.di.deps.findComponentDependencies
import com.nefrit.app.di.main.di.MainComponent
import com.nefrit.app.navigation.Navigator
import com.nefrit.common.base.BaseActivity
import com.nefrit.common.utils.AsyncResult
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>(){

    @Inject lateinit var navigator: Navigator

    private var navController: NavController? = null
    private val viewBinding:ActivityMainBinding by viewBinding(ActivityMainBinding::bind)


    override fun inject() {
        MainComponent.init(this, findComponentDependencies())
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun layoutResource(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navigator.attachNavController(navController!!, R.navigation.main_nav_graph)
        findViewById<BottomNavigationView>(R.id.menu_bnv).apply {
            setupWithNavController(navController!!)
        }
        viewBinding.menuBnv?.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(item, navController!!)
            return@setOnItemSelectedListener true
        }

    }

    override suspend fun subscribe(viewModel: MainViewModel) {
        with(viewBinding){
            viewModel.sharedPreferencesFlow.collect{result->
                when(result){
                    is AsyncResult.Success ->{
                        Log.e("Ayaz",result.toString()+" - activity")
                        if(result.getDataOrNull()!!){
                            menuBnv.visibility = View.VISIBLE
                        }else{
                            menuBnv.visibility = View.INVISIBLE
                        }
                    }
                    is AsyncResult.Error ->{
                        viewModel.errorHandlling(result.getExceptionOrNull()!!)
                    }
                    else ->{

                    }
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navController?.let {
            navigator.detachNavController(it)
        }
    }

}