package pl.org.seva.spacex.launchpad

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import pl.org.seva.spacex.R
import pl.org.seva.spacex.main.extension.invoke

class LaunchPadsFragment : Fragment(R.layout.fr_list) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (viewModels<LaunchPadViewModel>().value.ld to this) { response ->
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
