package homework.smd.ru.financetracker.models

data class CurrencyRate(
        val currency: Currency,
        val rate: Float
)

data class CurrencyModelAPI(
        val results: HashMap<String, CurrencyAPI>
) {
    data class CurrencyAPI(
            val fr: Currency,
            val `val`: Float
    )
    
    fun convertToCurrencyRate(): CurrencyRate? {
        val modelAPI: CurrencyAPI = this.results.values.first()
        return when(modelAPI.fr) {
            Currency.USD -> CurrencyRate(Currency.USD, modelAPI.`val`)
            Currency.EUR -> CurrencyRate(Currency.EUR, modelAPI.`val`)
            Currency.RUB -> CurrencyRate(Currency.RUB, modelAPI.`val`)
        }
    }
}