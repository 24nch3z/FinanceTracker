package homework.smd.ru.financetracker

import homework.smd.ru.financetracker.modules.Currency
import homework.smd.ru.financetracker.modules.Operation
import homework.smd.ru.financetracker.modules.OperationType
import homework.smd.ru.financetracker.modules.total
import org.junit.Test


class FinanceCalculatorTest {

    @Test
    fun testDifferentTypesInSimpleSum() {
        val op1 = Operation(10.2f, OperationType.SELL, Currency.RUBLE)
        val op2 = Operation(10.2f, OperationType.BUY, Currency.RUBLE)

        val list = arrayListOf(op1, op2)
        assert(list.total(10f).sum == 0.0f)
    }

    @Test
    fun testDifferentCurrenciesInSimpleSum() {
        val op1 = Operation(0.01f, OperationType.SELL, Currency.DOLLAR)
        val op2 = Operation(10f, OperationType.BUY, Currency.RUBLE)

        val ops = arrayListOf(op1, op2).total(1000f)
        assert(ops.sum == 0.0f)
    }

    @Test
    fun testTotalType() {
        val op1 = Operation(1f, OperationType.SELL, Currency.DOLLAR)
        val op2 = Operation(2f, OperationType.SELL, Currency.DOLLAR)
        val op3 = Operation(100f, OperationType.BUY, Currency.DOLLAR)
        val op4 = Operation(3f, OperationType.BUY, Currency.DOLLAR)

        val ops = arrayListOf(op1, op2, op3, op4).total(10f)
        assert(ops.type == OperationType.BUY)
    }

    @Test
    fun testTotalCurrency() {
        val op1 = Operation(1f, OperationType.SELL, Currency.RUBLE)
        val op2 = Operation(1f, OperationType.SELL, Currency.DOLLAR)
        val op3 = Operation(1f, OperationType.SELL, Currency.RUBLE)

        val ops = arrayListOf(op1, op2, op3).total(10f, currency = Currency.DOLLAR)
        assert(ops.currency == Currency.DOLLAR)
    }


    @Test
    fun testComplicityCalculation() {
        val op1 = Operation(10f, OperationType.SELL, Currency.RUBLE)
        val op2 = Operation(1f, OperationType.SELL, Currency.DOLLAR)
        val op3 = Operation(11f, OperationType.BUY, Currency.RUBLE)
        val op4 = Operation(1f, OperationType.BUY, Currency.DOLLAR)

        val resultOp = arrayListOf(op1, op2, op3, op4).total(10f, currency = Currency.RUBLE)
        val correctOp = Operation(1f, OperationType.BUY, Currency.RUBLE)

        assert(resultOp.sum.toInt() == correctOp.sum.toInt())
        assert(resultOp.type == correctOp.type)
        assert(resultOp.currency == correctOp.currency)
    }
}