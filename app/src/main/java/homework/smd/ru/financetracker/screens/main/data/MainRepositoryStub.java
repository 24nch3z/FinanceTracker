package homework.smd.ru.financetracker.screens.main.data;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

import homework.smd.ru.financetracker.models.Balance;
import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.CurrencyRate;
import homework.smd.ru.financetracker.screens.main.domain.MainRepository;
import io.reactivex.Flowable;

public class MainRepositoryStub implements MainRepository {

	@NonNull
	@Override
	public Flowable<List<Balance>> getUserBalances() {
		return Flowable.just(Arrays.asList(
			new Balance("Наличка", true, 42000f),
			new Balance("Зарплата", false, 100500f),
			new Balance("Карточка", false, -500f)
		));

	}

	@NonNull
	@Override
	public Flowable<List<CurrencyRate>> getCurrencyRates() {
		return Flowable.just(Arrays.asList(
			new CurrencyRate(Currency.USD, 62.80f, true),
			new CurrencyRate(Currency.EUR, 73.23f, false)
		));
	}

	@NonNull
	@Override
	public Flowable<CurrencyRate> getSystemCurrencyRate() {
		return Flowable.just(new CurrencyRate(Currency.USD, 65.47f, false));
	}
}
