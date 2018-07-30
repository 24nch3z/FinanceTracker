package homework.smd.ru.financetracker.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Operation(
        val sum: Double,
        val currency: Currency,
        var category: String?
) : Parcelable

fun Collection<Operation>.total(
        dollarRatio: Float,
        currency: Currency = Currency.RUB
): Operation {

    var amountInRuble = this.sumByDouble {
        if (it.currency == Currency.USD) {
            it.sum * dollarRatio
        } else {
            it.sum
        }
    }

    if (currency == Currency.USD) amountInRuble /= dollarRatio

    return Operation(amountInRuble, currency, null)
}
