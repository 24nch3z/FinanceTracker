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