package homework.smd.ru.financetracker.screens.main.domain;

import android.support.annotation.NonNull;

import java.util.List;

import homework.smd.ru.financetracker.datalayer.CurrencyRepository;
import homework.smd.ru.financetracker.datalayer.ExpenseRepository;
import homework.smd.ru.financetracker.datalayer.repositories.CurrencyRepositoryStub;
import homework.smd.ru.financetracker.datalayer.repositories.ExpenseRepositoryDataBase;
import homework.smd.ru.financetracker.models.CurrencyModelAPI;
import homework.smd.ru.financetracker.models.CurrencyRate;
import homework.smd.ru.financetracker.models.Expense;
import io.reactivex.Flowable;

public class MainInteractorImpl implements MainInteractor {

	private final ExpenseRepository expenseRepository = new ExpenseRepositoryDataBase();
	private final CurrencyRepository currencyRepository = new CurrencyRepositoryStub();

	@NonNull
	@Override
	public Flowable<List<Expense>> getUserExpenses() {
		return expenseRepository
			.getExpenses();
	}

	@NonNull
	@Override
	public Flowable<CurrencyRate> getCurrencyRates() {
		return currencyRepository
			.getCurrencyRates()
			.map(CurrencyModelAPI::convertToCurrencyRate)
			.filter(rate -> rate != null);
	}

	@NonNull
	@Override
	public Flowable<CurrencyRate> getSystemCurrencyRate() {
		return currencyRepository
			.getSystemCurrencyRate();
	}

	@Override
	public void updateExpense(Expense expense) {
		expenseRepository.updateExpense(expense);
	}
}
