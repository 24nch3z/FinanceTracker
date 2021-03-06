package homework.smd.ru.financetracker.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import homework.smd.ru.financetracker.database.Converters

@Entity
@TypeConverters(Converters::class)
class Operation {
    @PrimaryKey(autoGenerate = true) var id: Int = 0;
    var sum: Double = 0.0;
    var currency: Currency = Currency.USD;
    var category: String? = "";
    var expenseId: Int = 1;

    constructor()

    constructor(sum: Double, currency: Currency, category: String?, expenseId: Int) {
        this.sum = sum
        this.currency = currency
        this.category = category
        this.expenseId = expenseId
    }
}

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

    return Operation(amountInRuble, currency, null, -1)
}
