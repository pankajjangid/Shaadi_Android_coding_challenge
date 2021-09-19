package com.example.demoproject.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.StrictMode
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.demoproject.R
import com.example.demoproject.networking.MyApi
import com.example.demoproject.networking.NetworkConnectionInterceptor
import com.example.demoproject.repo.InviteRepository
import com.example.demoproject.room.AppDatabase
import com.example.demoproject.ui.invite.InviteViewModelFactory

import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class App : MultiDexApplication(), KodeinAware {


    companion object {

        var application: App? = null


        val TAG = "MyApp"


        fun getInstance(): App? {
            return application
        }

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)

    }

    override fun onCreate() {
        super.onCreate()

        application = this

        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())



    }


    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))
        bind() from singleton { AppDatabase(instance()) }

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }

        bind() from singleton { InviteRepository(instance(),instance()) }


        bind() from provider { InviteViewModelFactory(instance()) }



    }


}