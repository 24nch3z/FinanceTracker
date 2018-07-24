package homework.smd.ru.financetracker

import homework.smd.ru.financetracker.modules.Currency
import homework.smd.ru.financetracker.modules.Operation
import homework.smd.ru.financetracker.modules.OperationType
import homework.smd.ru.financetracker.modules.total
import org.junit.Assert
import org.junit.Test


class FinanceCalculatorTest {

    @Test
    fun testDifferentTypesInSimpleSum() {
        val op1 = Operation(10.2, OperationType.SELL, Currency.RUBLE)
        val op2 = Operation(10.2, OperationType.BUY, Currency.RUBLE)

        val result = arrayListOf(op1, op2).total(10f)
        Assert.assertEquals(result.sum, 0.0, 0.001)
    }

    @Test
    fun testDifferentCurrenciesInSimpleSum() {
        val op1 = Operation(0.01, OperationType.SELL, Currency.DOLLAR)
        val op2 = Operation(10.0, OperationType.BUY, Currency.RUBLE)

        val result = arrayListOf(op1, op2).total(1000f)
        Assert.assertEquals(result.sum, 0.0, 0.001)
    }

    @Test
    fun testTotalType() {
        val op1 = Operation(1.0, OperationType.SELL, Currency.DOLLAR)
        val op2 = Operation(2.0, OperationType.SELL, Currency.DOLLAR)
        val op3 = Operation(100.0, OperationType.BUY, Currency.DOLLAR)
        val op4 = Operation(3.0, OperationType.BUY, Currency.DOLLAR)

        val result = arrayListOf(op1, op2, op3, op4).total(10f)
        Assert.assertSame(result.type, OperationType.BUY)
    }

    @Test
    fun testTotalCurrency() {
        val op1 = Operation(1.0, OperationType.SELL, Currency.RUBLE)
        val op2 = Operation(1.0, OperationType.SELL, Currency.DOLLAR)
        val op3 = Operation(1.0, OperationType.SELL, Currency.RUBLE)

        val result = arrayListOf(op1, op2, op3).total(10f, currency = Currency.DOLLAR)
        Assert.assertSame(result.currency, Currency.DOLLAR)
    }


    @Test
    fun testComplicityCalculation() {
        val op1 = Operation(10.0, OperationType.SELL, Currency.RUBLE)
        val op2 = Operation(1.0, OperationType.SELL, Currency.DOLLAR)
        val op3 = Operation(11.0, OperationType.BUY, Currency.RUBLE)
        val op4 = Operation(1.0, OperationType.BUY, Currency.DOLLAR)

        val result = arrayListOf(op1, op2, op3, op4).total(10f, currency = Currency.RUBLE)
        val correct = Operation(1.0, OperationType.BUY, Currency.RUBLE)
        Assert.assertEquals(result, correct)
    }
}