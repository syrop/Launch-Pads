package pl.org.seva.myapplication.main.init

import androidx.appcompat.app.AppCompatDelegate

val bootstrap by instance<Bootstrap>()

class Bootstrap {

    fun boot() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}
