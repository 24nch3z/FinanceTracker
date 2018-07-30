package homework.smd.ru.financetracker.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Expense(
        val title: String,
        val operations: List<Operation>,
        val balance: Balance
) : Parcelable