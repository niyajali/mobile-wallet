/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifospay.core.data.domain.usecase.account

import com.mifospay.core.model.domain.Transaction
import com.mifospay.core.model.entity.accounts.savings.SavingsWithAssociations
import org.mifospay.core.data.base.UseCase
import org.mifospay.core.data.fineract.entity.mapper.TransactionMapper
import org.mifospay.core.data.fineract.repository.FineractRepository
import org.mifospay.core.data.util.Constants
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class FetchAccountTransactions @Inject constructor(
    private val fineractRepository: FineractRepository,
    private val transactionMapper: TransactionMapper,
) : UseCase<FetchAccountTransactions.RequestValues, FetchAccountTransactions.ResponseValue?>() {

    override fun executeUseCase(requestValues: RequestValues) {
        fineractRepository.getSelfAccountTransactions(requestValues.accountId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                object : Subscriber<SavingsWithAssociations>() {
                    override fun onCompleted() {}
                    override fun onError(e: Throwable) {
                        useCaseCallback.onError(
                            Constants.ERROR_FETCHING_REMOTE_ACCOUNT_TRANSACTIONS,
                        )
                    }

                    override fun onNext(transactions: SavingsWithAssociations) {
                        useCaseCallback.onSuccess(
                            ResponseValue(
                                transactionMapper.transformTransactionList(transactions),
                            ),
                        )
                    }
                },
            )
    }

    data class RequestValues(var accountId: Long) : UseCase.RequestValues
    data class ResponseValue(val transactions: List<Transaction>) : UseCase.ResponseValue
}
