package homework.smd.ru.financetracker.screens.main.domain;

import android.support.annotation.NonNull;

import homework.smd.ru.financetracker.datalayer.CurrencyRepository;
import homework.smd.ru.financetracker.datalayer.ExpenseRepository;
import homework.smd.ru.financetracker.datalayer.repositories.CurrencyRepositoryStub;
import homework.smd.ru.financetracker.datalayer.repositories.ExpenseRepositoryStub;
import homework.smd.ru.financetracker.models.Balance;
import homework.smd.ru.financetracker.models.CurrencyRate;
import homework.smd.ru.financetracker.models.UtilsKt;
import io.reactivex.Flowable;

public class MainInteractorImpl implements MainInteractor {

	private final ExpenseRepository expenseRepository = new ExpenseRepositoryStub();
	private final CurrencyRepository currencyRepository = new CurrencyRepositoryStub();

	@NonNull
	@Override
	public Flowable<Balance> getUserBalances() {
		return expenseRepository
			.getBalances()
			.flatMap(Flowable::fromIterable);
	}

	@NonNull
	@Override
	public Flowable<CurrencyRate> getCurrencyRates() {
		return currencyRepository
			.getCurrencyRates()
			.map(UtilsKt::getCurrency)
			.filter(rate -> rate != null);
	}

	@NonNull
	@Override
	public Flowable<CurrencyRate> getSystemCurrencyRate() {
		return currencyRepository
			.getSystemCurrencyRate();
	}
}
