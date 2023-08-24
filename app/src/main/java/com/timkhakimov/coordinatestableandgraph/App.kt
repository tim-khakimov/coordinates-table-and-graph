package com.timkhakimov.coordinatestableandgraph

import android.app.Application
import androidx.fragment.app.Fragment
import com.timkhakimov.coordinatestableandgraph.di.AppComponent
import com.timkhakimov.coordinatestableandgraph.di.DaggerAppComponent
import com.timkhakimov.coordinatestableandgraph.di.InteractorsModule
import com.timkhakimov.coordinatestableandgraph.di.NetworkModule
import com.timkhakimov.coordinatestableandgraph.di.RepositoriesModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule())
            .repositoriesModule(RepositoriesModule())
            .interactorsModule(InteractorsModule())
            .build()
    }
}

fun Fragment.appComponent() = (activity?.application as App).appComponent