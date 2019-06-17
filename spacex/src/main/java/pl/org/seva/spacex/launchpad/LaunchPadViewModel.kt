package pl.org.seva.spacex.launchpad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.coroutineScope
import pl.org.seva.spacex.main.api.spaceXService

class LaunchPadViewModel : ViewModel() {

    val ld = liveData(
        context = viewModelScope.coroutineContext,
        timeoutInMs = Long.MAX_VALUE) {
        coroutineScope {
            val response = spaceXService.all()
            if (response.isSuccessful) {
                emit(Status.Success(response.body()!!.map { it.toLaunchPad(this) }))
            }
            else {
                emit(Status.Error)
            }
        }
    }

    sealed class Status {
        object Error : Status()
        data class Success(val list: List<LaunchPad>) : Status()
    }
}
