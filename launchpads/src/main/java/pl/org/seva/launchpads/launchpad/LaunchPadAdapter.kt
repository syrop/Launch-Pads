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

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_launchpad.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import pl.org.seva.launchpads.R
import pl.org.seva.launchpads.main.extension.inflate
import pl.org.seva.launchpads.main.extension.invoke
import java.lang.IllegalStateException

@ExperimentalCoroutinesApi
class LaunchPadAdapter(
    private val list: List<LaunchPad>,
    private val scope: CoroutineScope,
    private val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<LaunchPadAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.row_launchpad))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lp = list[position]
        holder.name.text = lp.location.name
        holder.status.text = lp.status
        try {
            Picasso.get()
                .load(lp.thumbnail.getCompleted())
                .into(holder.thumbnail)
        }
        catch (e: IllegalStateException) {
            scope.launch {
                Picasso.get()
                    .load(lp.thumbnail.await())
                    .into(holder.thumbnail)
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init { view { onClick(adapterPosition) } }

        val thumbnail: ImageView = view.thumbnail
        val name: TextView = view.name
        val status: TextView = view.status
    }
}
