package homework.smd.ru.financetracker.modules


enum class OperationType {
    BUY,
    SELL
}
enum class Currency {
    USD,
    RUB
}
data class Operation(
        val sum: Double,
        val type: OperationType,
        val currency: Currency
)


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
