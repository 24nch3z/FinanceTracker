package homework.smd.ru.financetracker.modules

enum class OperationType {
    BUY,
    SELL
}
enum class Currency {
    DOLLAR,
    RUBLE
}
data class Operation(
        val sum: Float,
        val type: OperationType,
        val currency: Currency
)

fun Collection<Operation>.total(
        dollarRatio: Float,
        currency: Currency = Currency.RUBLE
): Operation {

    var amountInRuble = 0.0f
    this.forEach({
        var sum = it.sum
        if (it.currency == Currency.DOLLAR) sum *= dollarRatio
        if (it.type == OperationType.BUY) sum *= -1
        amountInRuble += sum
    })

    val type: OperationType
    if (amountInRuble < 0) {
        type = OperationType.BUY
        amountInRuble = -amountInRuble
    } else {
        type = OperationType.SELL
    }

    if (currency == Currency.DOLLAR) amountInRuble /= dollarRatio

    return Operation(amountInRuble, type, currency)
}
