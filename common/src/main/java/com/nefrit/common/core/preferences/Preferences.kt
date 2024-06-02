package com.nefrit.common.core.preferences

interface Preferences {

    fun saveAuthStatus(flag:Boolean)

    fun getAutStatus(): Boolean
    fun saveUserId(id:String)
    fun getUserId():String
}