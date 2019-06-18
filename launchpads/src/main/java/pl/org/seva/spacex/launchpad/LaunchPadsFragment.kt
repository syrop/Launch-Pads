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

package pl.org.seva.spacex.launchpad

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fr_launch_pads.*
import pl.org.seva.spacex.R
import pl.org.seva.spacex.main.extension.invoke
import pl.org.seva.spacex.main.extension.toast
import pl.org.seva.spacex.main.extension.verticalDivider

class LaunchPadsFragment : Fragment(R.layout.fr_launch_pads) {

    private val list by navGraphViewModels<ListVM>(R.id.nav_graph)
    private val single by navGraphViewModels<SingleVM>(R.id.nav_graph)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (list.ld to this) { response ->
            if (response is ListVM.Status.Success) {
                progress.visibility = View.GONE
                recycler.visibility = View.VISIBLE
                recycler.verticalDivider()
                recycler.adapter = LaunchPadAdapter(response.list, lifecycleScope)
                recycler.layoutManager = LinearLayoutManager(context)
            }
            else {
                getString(R.string.launch_pads_network_error).toast()
            }
        }
    }
}
