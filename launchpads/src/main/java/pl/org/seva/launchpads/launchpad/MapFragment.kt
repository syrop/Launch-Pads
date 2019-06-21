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

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pl.org.seva.launchpads.R
import pl.org.seva.launchpads.main.extension.invoke
import pl.org.seva.launchpads.main.extension.prefs
import pl.org.seva.launchpads.main.extension.title
import pl.org.seva.launchpads.main.ui.createMapHolder
import pl.org.seva.launchpads.main.ui.enableMyLocationOnResume

@ExperimentalCoroutinesApi
class MapFragment : Fragment(R.layout.fr_map) {

    private val single by navGraphViewModels<SingleVM>(R.id.nav_graph)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        title = single.launchPad.location.name
        createMapHolder(R.id.map) {
            prefs = prefs(SHARED_PREFERENCES_TAG)
        }.also { holder ->
            (holder.liveMap to this) { map ->
                enableMyLocationOnResume(map)
                val location = single.launchPad.location
                holder.markPosition(LatLng(
                    location.latitude,
                    location.longitude))
            }
        }
    }

    companion object {
        private const val SHARED_PREFERENCES_TAG = "fragment_map_preferences"
    }
}
