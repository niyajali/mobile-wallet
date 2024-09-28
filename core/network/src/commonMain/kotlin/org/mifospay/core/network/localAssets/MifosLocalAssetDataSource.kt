/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifospay.core.network.localAssets

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import org.mifospay.core.model.City
import org.mifospay.core.model.Country
import org.mifospay.core.model.State

class MifosLocalAssetDataSource(
    private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: LocalAssetManager,
) : LocalAssetDataSource {

    override suspend fun getCountries(): List<Country> {
        return emptyList()
    }

    override suspend fun getStateList(): List<State> {
        return emptyList()
    }

    override suspend fun getBanks(): List<String> {
        return emptyList()
    }

    override suspend fun getCities(): List<City> {
        return emptyList()
    }

    @Suppress("UnusedPrivateProperty")
    companion object {
        private const val COUNTRIES_ASSET = "countries.json"
        private const val STATES_ASSET = "states.json"
        private const val CITIES_ASSET = "cities.json"
        private const val BANKS_ASSET = "banks.json"
        private const val COUNTRIES_TO_CITIES_ASSET = "countriesToCities.json"
    }
}
