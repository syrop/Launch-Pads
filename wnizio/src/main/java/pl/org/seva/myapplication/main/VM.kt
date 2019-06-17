package pl.org.seva.myapplication.main

import android.os.Looper
import androidx.lifecycle.ViewModel

class VM : ViewModel() {

    fun main() = Looper.getMainLooper().thread === Thread.currentThread()

    override fun onCleared() {
        super.onCleared()
        println("wiktor on cleared")
    }
}
