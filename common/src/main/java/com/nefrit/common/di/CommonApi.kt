package com.nefrit.common.di

import android.content.Context
import com.nefrit.common.core.config.AppProperties
import com.nefrit.common.core.resources.ResourceManager

interface CommonApi {

    fun applicationContext(): Context

    fun provideResourceManager(): ResourceManager

    fun provideAppProperties(): AppProperties
}