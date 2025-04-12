package com.example.seekho.apiurls

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkMonitor.register(this)
    }
}