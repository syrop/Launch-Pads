package pl.org.seva.spacex.launchpad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import pl.org.seva.spacex.main.api.spaceXService

class LaunchPadViewModel : ViewModel() {

    val ld = liveData {
        emit(spaceXService.all())
    }
}
