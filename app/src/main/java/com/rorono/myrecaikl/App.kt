package com.rorono.myrecaikl

import android.app.Application
import com.rorono.data.Repository

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        Repository.init(this)
    }
}