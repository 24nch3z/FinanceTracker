package homework.smd.ru.financetracker.models

import java.util.*

enum class Currency {
    USD,
    RUB,
    EUR
}

fun moneyFormat(sum: Float): String {
    return String.format(Locale.getDefault(), "%,.2f", sum)
}
fun moneyFormat(sum: Double): String {
    return String.format(Locale.getDefault(), "%+,.2f", sum)
}

fun getCurrency(rawString: String): CurrencyRate? {
    // TODO parse via gson
    // {"USD_RUB":{"val":62.905302}}
    val floatPattern = """[0-9]*\.[0-9]*""".toRegex()
    val result = floatPattern.find(rawString)
    val rate: Float? = result?.value?.toFloat()
    var currency: Currency? = null
    if (rawString.contains(Currency.USD.toString())) {
        currency = Currency.USD
    }
    if (rawString.contains(Currency.EUR.toString())) {
        currency = Currency.EUR
    }
    return if (currency != null && rate != null) {
        CurrencyRate(currency, rate)
    } else {
        null
    }
}