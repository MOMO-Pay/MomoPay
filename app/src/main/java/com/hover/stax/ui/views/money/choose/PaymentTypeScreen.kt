package com.hover.stax.ui.views.money.choose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hover.stax.R
import com.hover.stax.ui.views.money.data.PaymentTypeItem
import com.hover.stax.ui.views.money.viewmodel.SendMoneyViewModel
import com.hover.stax.views.compose.StaxHeader
import com.hover.stax.views.compose.StaxLayout
import com.hover.stax.views.compose.StaxModalCell
import org.koin.androidx.compose.koinViewModel

@Composable
fun PaymentTypeScreen(
    viewModel: SendMoneyViewModel = koinViewModel(),
    onClickBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    StaxLayout(
        title = {
            StaxHeader(
                text = stringResource(R.string.payment_type_screen),
                onClickBack = onClickBack
            )
        },
        content = {
            items(state.items) { item ->
                PaymentTypeItem(
                    onClick = {
//                        viewModel.dispatch(ThemeAction.SelectTheme(item))
                    },
                    item = item
                )
                Spacer(Modifier.height(8.dp))
            }
        }
    )
}

@SuppressLint("InlinedApi")
@Composable
private fun PaymentTypeItem(
    onClick: () -> Unit,
    item: PaymentTypeItem,
) {
    StaxModalCell(
        onClick = onClick,
        text = stringResource(item.momo.label),
        color = if (item.applied) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        },
        textColor = if (item.applied) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        },
        leftIcon = null,
        rightIcon = if (item.applied) {
            @Composable {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }
        } else {
            null
        }
    )
}