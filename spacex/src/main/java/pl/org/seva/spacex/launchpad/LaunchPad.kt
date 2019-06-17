package pl.org.seva.spacex.launchpad

import kotlinx.coroutines.Deferred

data class LaunchPad(val status: String, val location: Location, val thumbnail: Deferred<String>, val site_id: String)
