package homework.smd.ru.financetracker.models

data class CurrencyRate(
        val currency: Currency,
        val rate: Float,
        val isGrow: Boolean
)
