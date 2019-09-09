/*
 * Copyright (C) 2019 Wiktor Nizio
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * If you like this program, consider donating bitcoin: bc1qncxh5xs6erq6w4qz3a7xl7f50agrgn3w58dsfp
 */

package pl.org.seva.launchpads.launchpad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.coroutineScope
import pl.org.seva.launchpads.main.api.SpaceXService

class LaunchPadListVM(private val spaceXService: SpaceXService) : ViewModel() {

    val liveData = liveData(
        context = viewModelScope.coroutineContext,
        timeoutInMs = Long.MAX_VALUE) {
        coroutineScope {
            val response = spaceXService.getAll()
            if (response.isSuccessful) {
                emit(Status.Success(checkNotNull(response.body()).map { it.toLaunchPad(this) }))
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