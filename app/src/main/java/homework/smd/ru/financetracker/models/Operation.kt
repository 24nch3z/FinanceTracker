package homework.smd.ru.financetracker.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Operation(
        val sum: Double,
        val type: OperationType,
        val currency: Currency
) : Parcelable

fun Collection<Operation>.total(
        dollarRatio: Float,
        currency: Currency = Currency.RUB
): Operation {

    var amountInRuble = this.sumByDouble {
        var sum = it.sum
        if (it.currency == Currency.USD) sum *= dollarRatio
        if (it.type == OperationType.BUY) sum *= -1
        sum
    }

    val type: OperationType
    if (amountInRuble < 0) {
        type = OperationType.BUY
        amountInRuble = -amountInRuble
    } else {
        type = OperationType.SELL
    }

    if (currency == Currency.USD) amountInRuble /= dollarRatio

    return Operation(amountInRuble, type, currency)
}
