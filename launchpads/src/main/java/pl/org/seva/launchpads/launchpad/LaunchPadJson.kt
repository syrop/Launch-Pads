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

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import pl.org.seva.launchpads.main.api.wikipediaService

@ExperimentalCoroutinesApi
data class LaunchPadJson(
    val status: String,
    val location: Location,
    val wikipedia: String,
    val site_id: String) {

    fun toLaunchPad(scope: CoroutineScope) = LaunchPad(
        status,
        location,
        scope.async { getThumbnail() },
        site_id
    )

    private suspend fun getThumbnail(): String {
        val response = wikipediaService.getSummary(wikipedia.replace(PREFIX, ""))
        return if (response.isSuccessful) checkNotNull(response.body()).thumbnail.source else ""
    }

    companion object {
        const val PREFIX = "https://en.wikipedia.org/wiki/"
    }
}
