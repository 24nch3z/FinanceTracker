package homework.smd.ru.financetracker.models

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Balance(
        val balanceName: String,
        var isVisible: Boolean,
        var sum: Float
) : Parcelable {

    @IgnoredOnParcel var stringSum: String = ""

    fun changeVisibility() { isVisible = !isVisible }
}