package org.mifospay.feature.bank.accounts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mifospay.core.model.domain.BankAccountDetails
import org.mifospay.core.designsystem.component.MifosCard

@Composable
fun AccountsItem(
    bankAccountDetails: BankAccountDetails,
    onAccountClicked: () -> Unit
) {
    MifosCard(
        onClick = { onAccountClicked.invoke() },
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.feature_accounts_ic_bank),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 16.dp, end = 16.dp)
                        .size(39.dp)
                )

                Column {
                    Text(
                        text = bankAccountDetails.accountholderName.toString(),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = bankAccountDetails.bankName.toString(),
                        modifier = Modifier.padding(top = 4.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = bankAccountDetails.branch.toString(),
                        modifier = Modifier.padding(16.dp),
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountsItemPreview() {
    AccountsItem(
        bankAccountDetails = BankAccountDetails("A", "B", "C"),
        onAccountClicked = {}
    )
}