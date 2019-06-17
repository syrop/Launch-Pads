package pl.org.seva.spacex.launchpad

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import pl.org.seva.spacex.R
import pl.org.seva.spacex.main.api.spaceXService

class LaunchPadsFragment : Fragment(R.layout.fr_list) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lifecycleScope.launch {
            val response = spaceXService.all()
            println("wiktor response is successful: ${response.isSuccessful}")
            println("wiktor response: ${response.body()}")
        }
    }
}
