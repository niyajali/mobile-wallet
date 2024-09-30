/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifospay.core.common

import co.touchlab.kermit.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.SYSTEM

interface FileUtils {
    suspend fun writeInputStreamDataToFile(inputStream: ByteArray, filePath: String): Boolean

    companion object {
        val logger = Logger.withTag("FileUtils")
    }
}

expect fun createPlatformFileUtils(): FileUtils

class CommonFileUtils : FileUtils {
    override suspend fun writeInputStreamDataToFile(
        inputStream: ByteArray,
        filePath: String,
    ): Boolean =
        withContext(Dispatchers.Default) {
            try {
                val path = filePath.toPath()
                FileSystem.SYSTEM.write(path) {
                    write(inputStream)
                }

                true
            } catch (e: Exception) {
                FileUtils.logger.e { "Error writing file: ${e.message}" }
                false
            }
        }
}
