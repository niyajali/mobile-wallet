/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifospay.feature.profile

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.mifospay.core.common.Result
import org.mifospay.core.data.repository.ClientRepository
import org.mifospay.core.datastore.UserPreferencesRepository
import org.mifospay.core.model.client.Client
import org.mifospay.core.ui.utils.BaseViewModel
import org.mifospay.feature.profile.ProfileAction.Internal.HandleLoadClientImageResult
import org.mifospay.feature.profile.ProfileAction.Internal.HandleLoadClientResult
import org.mifospay.feature.profile.ProfileAction.Internal.LoadClient
import org.mifospay.feature.profile.ProfileAction.Internal.LoadClientImage

internal class ProfileViewModel(
    private val preferencesRepository: UserPreferencesRepository,
    private val clientRepository: ClientRepository,
) : BaseViewModel<ProfileState, ProfileEvent, ProfileAction>(
    initialState = run {
        val clientId = requireNotNull(preferencesRepository.clientId.value)
        ProfileState(
            clientId = clientId,
            viewState = ProfileState.ViewState.Loading,
        )
    },
) {
    init {
        viewModelScope.launch {
            sendAction(LoadClient(state.clientId))
            sendAction(LoadClientImage(state.clientId))
        }
    }

    override fun handleAction(action: ProfileAction) {
        when (action) {
            ProfileAction.NavigateToEditProfile -> {
                sendEvent(ProfileEvent.OnEditProfile)
            }

            ProfileAction.NavigateToLinkBankAccount -> {
                sendEvent(ProfileEvent.OnLinkBankAccount)
            }

            ProfileAction.ShowPersonalQRCode -> {
                sendEvent(ProfileEvent.ShowQRCode)
            }

            ProfileAction.DismissErrorDialog -> {
                mutableStateFlow.update {
                    it.copy(dialogState = null)
                }
            }

            is HandleLoadClientImageResult -> handleLoadClientImageResult(action)

            is LoadClientImage -> loadClientImage(action)

            is LoadClient -> loadClient(action)

            is HandleLoadClientResult -> handleClientResult(action)

            is ProfileAction.RefreshProfile -> {
                viewModelScope.launch {
                    sendAction(LoadClient(state.clientId))
                }
            }
        }
    }

    private fun handleLoadClientImageResult(action: HandleLoadClientImageResult) {
        when (action.result) {
            is Result.Success -> {
                mutableStateFlow.update {
                    it.copy(clientImage = action.result.data)
                }
            }

            is Result.Error -> {
//                mutableStateFlow.update {
//                    it.copy(dialogState = Error(action.result.exception.message ?: ""))
//                }
            }

            is Result.Loading -> {
                mutableStateFlow.update {
                    it.copy(dialogState = ProfileState.DialogState.Loading)
                }
            }
        }
    }

    private fun loadClientImage(action: LoadClientImage) {
        clientRepository.getClientImage(action.clientId).onEach {
            sendAction(HandleLoadClientImageResult(it))
        }.launchIn(viewModelScope)
    }

    private fun loadClient(action: LoadClient) {
        clientRepository
            .getClientInfo(action.clientId)
            .onEach { sendAction(HandleLoadClientResult(it)) }
            .launchIn(viewModelScope)
    }

    private fun handleClientResult(action: HandleLoadClientResult) {
        when (action.result) {
            is Result.Error -> {
                mutableStateFlow.update {
                    it.copy(
                        viewState = ProfileState.ViewState.Error(
                            action.result.exception.message ?: "",
                        ),
                    )
                }
            }

            is Result.Loading -> {
                mutableStateFlow.update {
                    it.copy(viewState = ProfileState.ViewState.Loading)
                }
            }

            is Result.Success -> {
                mutableStateFlow.update {
                    it.copy(viewState = ProfileState.ViewState.Success(action.result.data))
                }

                viewModelScope.launch {
                    preferencesRepository.updateClientInfo(action.result.data)
                }
            }
        }
    }
}

internal data class ProfileState(
    val clientId: Long,
    val viewState: ViewState = ViewState.Loading,
    val clientImage: String? = null,
    val dialogState: DialogState? = null,
) {
    sealed interface ViewState {
        data object Loading : ViewState
        data class Error(val message: String) : ViewState
        data class Success(val client: Client) : ViewState
    }

    sealed interface DialogState {
        data object Loading : DialogState

        data class Error(val message: String) : DialogState
    }
}

internal sealed interface ProfileEvent {
    data object OnEditProfile : ProfileEvent
    data object OnLinkBankAccount : ProfileEvent
    data object ShowQRCode : ProfileEvent
}

internal sealed interface ProfileAction {
    data object NavigateToEditProfile : ProfileAction
    data object NavigateToLinkBankAccount : ProfileAction
    data object ShowPersonalQRCode : ProfileAction

    data object DismissErrorDialog : ProfileAction
    data object RefreshProfile : ProfileAction

    sealed interface Internal : ProfileAction {
        data class LoadClientImage(val clientId: Long) : Internal
        data class HandleLoadClientImageResult(val result: Result<String>) : Internal

        data class LoadClient(val clientId: Long) : Internal
        data class HandleLoadClientResult(val result: Result<Client>) : Internal
    }
}
