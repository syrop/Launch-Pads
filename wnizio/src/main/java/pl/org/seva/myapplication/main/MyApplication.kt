package pl.org.seva.myapplication.main

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.conf.global
import pl.org.seva.myapplication.main.init.bootstrap
import pl.org.seva.myapplication.main.init.module

@Suppress("unused")
class MyApplication : Application() {
    init {
        Kodein.global.addImport(module)
    }

    override fun onCreate() {
        super.onCreate()
        bootstrap.boot()
    }
}
