package homework.smd.ru.financetracker.database

import android.arch.persistence.room.TypeConverter
import homework.smd.ru.financetracker.models.Currency

class Converters {

    @TypeConverter
    fun operationCurrencyFromEnumToString(currency: Currency) = currency.toString()

    @TypeConverter
    fun operationCurrencyFromStringToEnum(currency: String): Currency {
        return when (currency) {
            "RUB" -> Currency.RUB
            "USD" -> Currency.USD
            "EUR" -> Currency.EUR
            else -> Currency.RUB
        }
    }
}