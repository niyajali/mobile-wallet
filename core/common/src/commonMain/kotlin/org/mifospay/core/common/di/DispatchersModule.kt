/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifospay.core.common.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.mifospay.core.common.MifosDispatchers

val DispatchersModule = module {
    single<CoroutineDispatcher>(named(MifosDispatchers.IO.name)) { Dispatchers.IO }
    single<CoroutineDispatcher>(named(MifosDispatchers.Default.name)) { Dispatchers.Default }
    single<CoroutineDispatcher>(named(MifosDispatchers.Unconfined.name)) { Dispatchers.Unconfined }
    single<CoroutineScope>(named("ApplicationScope")) {
        CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }
}
