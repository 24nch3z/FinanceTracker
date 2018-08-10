package homework.smd.ru.financetracker.screens.main.domain;

import android.support.annotation.NonNull;

import java.util.List;

import homework.smd.ru.financetracker.models.CurrencyRate;
import homework.smd.ru.financetracker.models.Expense;
import io.reactivex.Flowable;

public interface MainInteractor {

	@NonNull Flowable<List<Expense>> getUserExpenses();

	@NonNull Flowable<CurrencyRate> getCurrencyRates();

	@NonNull Flowable<CurrencyRate> getSystemCurrencyRate();

	void updateExpense(Expense expense);
}
