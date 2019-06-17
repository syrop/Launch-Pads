package pl.org.seva.spacex.launchpad

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import kotlinx.coroutines.launch
import pl.org.seva.spacex.R
import pl.org.seva.spacex.main.extension.invoke

class LaunchPadsFragment : Fragment(R.layout.fr_list) {

    private val vm by navGraphViewModels<LaunchPadViewModel>(R.id.nav_graph)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (vm.ld to this) { response ->
            if (response is LaunchPadViewModel.Status.Success) {
                for (launchPad in response.list) {
                    lifecycleScope.launch {
                        println("wiktor ${launchPad.location.name} thumbnail: ${launchPad.thumbnail.await()}")
                    }
                }
            }
        }
    }
}
