package homework.smd.ru.financetracker.database

import android.annotation.SuppressLint
import android.arch.persistence.room.TypeConverter
import homework.smd.ru.financetracker.models.Currency
import java.text.SimpleDateFormat
import java.util.*

class Converters {

    companion object {
        const val DATE_FORMAT = "dd/MM/yyyy"
    }

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
    @SuppressLint("SimpleDateFormat")
    fun fromDateToString(date: Date) = SimpleDateFormat(DATE_FORMAT).format(date)

    @TypeConverter
    @SuppressLint("SimpleDateFormat")
    fun fromStringToDate(str: String) = SimpleDateFormat(DATE_FORMAT).parse(str)
}