package homework.smd.ru.financetracker

import homework.smd.ru.financetracker.models.Currency
import homework.smd.ru.financetracker.models.Operation
import homework.smd.ru.financetracker.models.total
import org.junit.Assert
import org.junit.Test


class FinanceCalculatorTest {

    @Test
    fun testDifferentTypesInSimpleSum() {
        val op1 = Operation(10.2, Currency.RUB, null, 0)
        val op2 = Operation(-10.2, Currency.RUB, null, 0)

        val result = arrayListOf(op1, op2).total(10f)
        Assert.assertEquals(result.sum, 0.0, 0.001)
    }

    @Test
    fun testDifferentCurrenciesInSimpleSum() {
        val op1 = Operation(0.01, Currency.USD, null, 0)
        val op2 = Operation(-10.0, Currency.RUB, null, 0)

        val result = arrayListOf(op1, op2).total(1000f)
        Assert.assertEquals(result.sum, 0.0, 0.001)
    }

    @Test
    fun testTotalSum() {
        val op1 = Operation(1.0,Currency.USD, null, 0)
        val op2 = Operation(2.0, Currency.USD, null, 0)
        val op3 = Operation(-100.0, Currency.USD, null, 0)
        val op4 = Operation(-3.0, Currency.USD, null, 0)

        val result = arrayListOf(op1, op2, op3, op4).total(10f)
        Assert.assertTrue(result.sum < 0)
    }

    @Test
    fun testTotalCurrency() {
        val op1 = Operation(1.0, Currency.RUB, null, 0)
        val op2 = Operation(1.0, Currency.USD, null, 0)
        val op3 = Operation(1.0, Currency.RUB, null, 0)

        val result = arrayListOf(op1, op2, op3).total(10f, currency = Currency.USD)
        Assert.assertSame(result.currency, Currency.USD)
    }


    @Test
    fun testComplicityCalculation() {
        val op1 = Operation(10.0, Currency.RUB, null, 0)
        val op2 = Operation(1.0, Currency.USD, null, 0)
        val op3 = Operation(-11.0, Currency.RUB, null, 0)
        val op4 = Operation(-1.0, Currency.USD, null, 0)

        val result = arrayListOf(op1, op2, op3, op4).total(10f, currency = Currency.RUB)
        val correct = Operation(-1.0, Currency.RUB, null, 0)
        Assert.assertEquals(result, correct)
    }
}