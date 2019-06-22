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

package pl.org.seva.launchpads.main.extension

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.nav(@IdRes resId: Int): Boolean {
    findNavController().navigate(resId)
    return true
}

fun Fragment.prefs(name: String): SharedPreferences =
    requireContext().getSharedPreferences(name, Context.MODE_PRIVATE)

fun Fragment.checkPermission(permission: String) =
    ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED

var Fragment.title: CharSequence get() = (requireActivity() as AppCompatActivity).supportActionBar!!.title!!
    set(value) {
        (requireActivity() as AppCompatActivity).supportActionBar!!.title = value
    }

fun Fragment.toast(@StringRes id: Int) = Toast.makeText(requireContext(), getString(id), Toast.LENGTH_SHORT).show()
