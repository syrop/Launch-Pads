package pl.org.seva.myapplication.main

import android.os.Bundle
import android.system.StructUtsname
import androidx.fragment.app.Fragment
import androidx.lifecycle.liveData
import kotlinx.coroutines.*
import pl.org.seva.myapplication.R
import pl.org.seva.myapplication.main.init.instance
import kotlin.coroutines.EmptyCoroutineContext
import pl.org.seva.myapplication.main.extension.observe

class MainFragment : Fragment(R.layout.fr_main) {

    private val unit = Unit

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        GlobalScope.launch {
            val scope = CoroutineScope(Job() + Dispatchers.Main)
            scope.launch {
                withContext(NonCancellable) {
                    println("wiktor inside the block")
                    delay(2000)
                    println("wiktor finished successfully")
                }
                yield()
                println("wiktor not cancelled")
            }
            delay(1000)
            scope.cancel()
            println("wiktor canceled")
        }
    }
}
