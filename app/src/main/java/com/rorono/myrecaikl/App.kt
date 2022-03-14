package com.rorono.myrecaikl

import android.app.Application

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        Repository.init(this)
    }
}