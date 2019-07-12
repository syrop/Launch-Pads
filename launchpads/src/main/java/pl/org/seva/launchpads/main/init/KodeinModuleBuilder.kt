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

package pl.org.seva.launchpads.main.init

import android.os.Build
import org.kodein.di.Kodein
import org.kodein.di.KodeinProperty
import org.kodein.di.conf.global
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.multiton
import org.kodein.di.generic.singleton
import pl.org.seva.launchpads.BuildConfig
import pl.org.seva.launchpads.main.api.*
import java.util.logging.Logger

val module get() = KodeinModuleBuilder().build()

inline fun <reified T : Any> instance() = Kodein.global.instance<T>()

inline val <T> KodeinProperty<T>.value get() = provideDelegate(null, Build::ID).value

class KodeinModuleBuilder {

    fun build() = Kodein.Module("main") {
        bind<Bootstrap>() with singleton { Bootstrap() }
        bind<Logger>() with multiton { tag: String ->
            checkNotNull(Logger.getLogger(tag)).apply {
                if (!BuildConfig.DEBUG) {
                    @Suppress("UsePropertyAccessSyntax")
                    setFilter { false }
                }
            }
        }
        bind<SpaceXServiceFactory>() with singleton { SpaceXServiceFactory() }
        bind<SpaceXService>() with singleton { spaceXServiceFactory.getLaunchPadService() }
        bind<WikipediaServiceFactory>() with singleton { WikipediaServiceFactory() }
        bind<WikipediaService>() with singleton { wikipediaServiceFactory.getWikipediaService() }
    }
}
