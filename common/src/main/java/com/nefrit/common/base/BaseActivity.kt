package com.nefrit.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nefrit.common.R
import com.nefrit.common.utils.setBarColorBackground
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    @Inject
    protected open lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource())
        setBarColorBackground(R.color.colorPrimaryDark)
        inject()
        initViews()
        lifecycleScope.launch {
            subscribe(viewModel)
        }
    }

    abstract fun inject()

    abstract fun layoutResource(): Int

    abstract fun initViews()

    abstract suspend fun subscribe(viewModel: T)
}