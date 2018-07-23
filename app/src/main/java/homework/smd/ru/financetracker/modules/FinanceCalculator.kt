package homework.smd.ru.financetracker.modules

import homework.smd.ru.financetracker.App
import javax.inject.Inject


enum class OperationType {
    BUY,
    SELL
}
enum class Currency {
    DOLLAR,
    RUBLE
}
data class Operation(
        val sum: Float,
        val type: OperationType,
        val currency: Currency
) {
    @Inject lateinit var configuration: Configuration

    init {
        App.getComponent().inject(this)
    }

    operator fun plus(operation: Operation): Operation {
        var sum: Float
        val dollarRate = configuration.dollarRatio


        if (this.currency == operation.currency) {
            sum = operation.sum
        } else {
            sum = when (this.currency) {
                Currency.DOLLAR -> operation.sum * dollarRate
                Currency.RUBLE -> operation.sum / dollarRate
            }
        }


        if (this.type == operation.type) {
            sum += this.sum
        } else {
            sum = this.sum - sum
        }


        val type: OperationType
        if (sum < 0) {
            type = when(this.type) {
                OperationType.BUY -> OperationType.SELL
                OperationType.SELL -> OperationType.BUY
            }
        } else {
            type = this.type
        }


        return Operation(sum, type, this.currency)
    }
}

fun Collection<Operation>.total(isRuble: Boolean = true): Float {

    val dollarRatio = if (this.take(0).isNotEmpty()) {
        this.take(0)[0].configuration.dollarRatio
    } else 0f
    var amountInRuble = 0.0f

    this.forEach( {
        var sum = it.sum;
        if (it.currency == Currency.DOLLAR) sum *= dollarRatio
        if (it.type == OperationType.BUY) sum *= -1
        amountInRuble += sum
    })

    return if (isRuble) amountInRuble else amountInRuble*dollarRatio
}


