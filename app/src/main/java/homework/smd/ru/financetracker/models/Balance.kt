package homework.smd.ru.financetracker.models

data class Balance(
        val balanceName: String,
        var isVisible: Boolean,
        var sum: Float
) {
    var stringSum: String = ""

    fun changeVisibility() {
        isVisible = !isVisible
    }
}