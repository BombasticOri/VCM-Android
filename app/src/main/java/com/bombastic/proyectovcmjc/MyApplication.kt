package com.bombastic.proyectovcmjc

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.bombastic.proyectovcmjc.util.isNight
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@HiltAndroidApp
class MyApplication : Application(){
    override fun onCreate(){
        super.onCreate()
        val mode=if(isNight()){
            AppCompatDelegate.MODE_NIGHT_YES
        }else{
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}