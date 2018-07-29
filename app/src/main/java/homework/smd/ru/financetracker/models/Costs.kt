package homework.smd.ru.financetracker.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Costs(
        val title: String,
        val operations: List<Operation>
) : Parcelable