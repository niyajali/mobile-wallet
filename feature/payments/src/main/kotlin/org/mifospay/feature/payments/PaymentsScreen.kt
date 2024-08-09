package org.mifospay.feature.payments

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.rememberPagerState
import com.mifospay.core.model.domain.Transaction
import org.mifospay.core.ui.MifosScrollableTabRow
import org.mifospay.core.ui.utility.TabContent
import org.mifospay.feature.history.HistoryScreen
import org.mifospay.feature.invoices.InvoiceScreenRoute
import org.mifospay.feature.send.money.SendScreenRoute
import org.mifospay.feature.standing.instruction.StandingInstructionsScreenRoute

@Composable
fun PaymentsRoute(
    viewModel: TransferViewModel = hiltViewModel(),
    showQr: (String) -> Unit,
    onNewSI: () -> Unit,
    viewReceipt: (String) -> Unit,
    onAccountClicked: (String, ArrayList<Transaction>) -> Unit,
    proceedWithMakeTransferFlow: (String, String) -> Unit,
    navigateToInvoiceDetailScreen: (Uri) -> Unit,
) {
    val vpa by viewModel.vpa.collectAsStateWithLifecycle()
    PaymentScreenContent(
        vpa = vpa,
        showQr = showQr,
        onNewSI = onNewSI,
        onAccountClicked = onAccountClicked,
        viewReceipt = viewReceipt,
        proceedWithMakeTransferFlow = proceedWithMakeTransferFlow,
        navigateToInvoiceDetailScreen = navigateToInvoiceDetailScreen,
    )
}

@Composable
fun PaymentScreenContent(
    vpa: String,
    showQr: (String) -> Unit,
    onNewSI: () -> Unit,
    viewReceipt: (String) -> Unit,
    onAccountClicked: (String, ArrayList<Transaction>) -> Unit,
    proceedWithMakeTransferFlow: (String, String) -> Unit,
    navigateToInvoiceDetailScreen: (Uri) -> Unit,
) {
    val tabContents = listOf(
        TabContent(PaymentsScreenContents.SEND.name) {
            SendScreenRoute(
                onBackClick = {},
                showToolBar = false,
                proceedWithMakeTransferFlow = proceedWithMakeTransferFlow,
            )
        },
        TabContent(PaymentsScreenContents.REQUEST.name) {
            RequestScreen(showQr = { showQr.invoke(vpa) })
        },
        TabContent(PaymentsScreenContents.HISTORY.name) {
            HistoryScreen(
                accountClicked = onAccountClicked,
                viewReceipt = viewReceipt,
            )
        },
        TabContent(PaymentsScreenContents.SI.name) {
            StandingInstructionsScreenRoute(onNewSI = { onNewSI.invoke() })
        },
        TabContent(PaymentsScreenContents.INVOICES.name) {
            InvoiceScreenRoute(
                navigateToInvoiceDetailScreen = navigateToInvoiceDetailScreen,
            )
        },
    )

    val pagerState = rememberPagerState(initialPage = 0)

    Column(modifier = Modifier.fillMaxSize()) {
        MifosScrollableTabRow(
            tabContents = tabContents,
            pagerState = pagerState,
        )
    }
}

enum class PaymentsScreenContents {
    SEND,
    REQUEST,
    HISTORY,
    SI,
    INVOICES
}

@Preview(showBackground = true)
@Composable
fun PaymentsScreenPreview() {
    PaymentScreenContent(vpa = "", { _ -> }, {}, {}, { _, _ -> }, { _, _ -> }, {})
}