package homework.smd.ru.financetracker.database

import android.arch.persistence.room.TypeConverter
import homework.smd.ru.financetracker.models.Currency
import java.util.*

class Converters {

    @TypeConverter
    fun fromEnumToString(currency: Currency) = currency.toString()

    @TypeConverter
    fun fromStringToEnum(currency: String): Currency {
        return when (currency) {
            "RUB" -> Currency.RUB
            "USD" -> Currency.USD
            "EUR" -> Currency.EUR
            else -> Currency.RUB
        }
    }

    @TypeConverter
    fun fromDateToLong(date: Date) = date.time

    @TypeConverter
    fun fromLongToDate(time: Long) = Date(time)
}