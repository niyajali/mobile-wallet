package org.mifospay.feature.upiSetup.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mifospay.core.model.domain.BankAccountDetails
import org.mifospay.common.Constants
import org.mifospay.core.designsystem.theme.MifosTheme
import org.mifospay.feature.upiSetup.viewmodel.SetUpUpiViewModal

@Composable
fun SetUpUpiScreenContent(
    viewModal: SetUpUpiViewModal = hiltViewModel(),
    bankAccountDetails: BankAccountDetails,
    type: String,
    correctlySettingUpi: (String) -> Unit
) {
    Column {
        if (type == Constants.CHANGE) {
            ChangeUpi(
                viewModal.requestOtp(bankAccountDetails), correctlySettingUpi
            )
        } else {
            SettingAndForgotUpi(
                correctlySettingUpi
            )
        }
    }
}

@Composable
fun SettingAndForgotUpi(
    correctlySettingUpi: (String) -> Unit
) {
    var debitCardVerified by rememberSaveable { mutableStateOf(false) }
    var otpVerified by rememberSaveable { mutableStateOf(false) }
    var debitCardScreenVisible by rememberSaveable { mutableStateOf(true) }
    var otpScreenVisible by rememberSaveable { mutableStateOf(false) }
    var upiPinScreenVisible by rememberSaveable { mutableStateOf(false) }
    var upiPinScreenVerified by rememberSaveable { mutableStateOf(false) }
    var realOtp by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

    DebitCardScreen(verificationStatus = debitCardVerified,
        isContentVisible = debitCardScreenVisible,
        onDebitCardVerified = {
            debitCardVerified = true
            otpScreenVisible = true
            realOtp = it
            debitCardScreenVisible = false
        },
        onDebitCardVerificationFailed = {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    OtpScreen(verificationStatus = otpVerified,
        contentVisibility = otpScreenVisible,
        realOtp = realOtp,
        onOtpTextCorrectlyEntered = {
            otpScreenVisible = false
            otpVerified = true
            upiPinScreenVisible = true
        })
    UpiPinScreen(contentVisibility = upiPinScreenVisible, correctlySettingUpi = {
        upiPinScreenVerified = true
        correctlySettingUpi(it)
    })
}

@Composable
fun ChangeUpi(
    otpText: String, correctlySettingUpi: (String) -> Unit
) {
    var otpVerified by rememberSaveable { mutableStateOf(false) }
    var otpScreenVisible by rememberSaveable { mutableStateOf(true) }
    var upiPinScreenVisible by rememberSaveable { mutableStateOf(false) }
    var upiPinScreenVerified by rememberSaveable { mutableStateOf(false) }
    var realOtp by rememberSaveable { mutableStateOf(otpText) }

    OtpScreen(verificationStatus = otpVerified,
        contentVisibility = otpScreenVisible,
        realOtp = realOtp,
        onOtpTextCorrectlyEntered = {
            otpScreenVisible = false
            Log.i("OtpScreen", "$otpScreenVisible")
            otpVerified = true
            upiPinScreenVisible = true
            otpScreenVisible = false
        })
    UpiPinScreen(contentVisibility = upiPinScreenVisible, correctlySettingUpi = {
        upiPinScreenVerified = true
        correctlySettingUpi(it)
    })
}


@Preview
@Composable
fun PreviewSetUpUpiPin() {
    MifosTheme {
        SetUpUpiScreenContent(bankAccountDetails = BankAccountDetails(),
            type = Constants.SETUP,
            correctlySettingUpi = {})
    }
}

@Preview
@Composable
fun PreviewForgetUpiPin() {
    MifosTheme {
        SetUpUpiScreenContent(bankAccountDetails = BankAccountDetails(),
            type = Constants.FORGOT,
            correctlySettingUpi = {})
    }
}

@Preview
@Composable
fun PreviewChangeUpiPin() {
    MifosTheme {
        SetUpUpiScreenContent(bankAccountDetails = BankAccountDetails(),
            type = Constants.CHANGE,
            correctlySettingUpi = {})
    }
}