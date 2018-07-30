package homework.smd.ru.financetracker.datalayer.repositories;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

import homework.smd.ru.financetracker.datalayer.CurrencyRepository;
import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.CurrencyRate;
import io.reactivex.Flowable;

public class CurrencyRepositoryStub implements CurrencyRepository {

	private final CurrencyRate usd =
		new CurrencyRate(Currency.USD, 68.56f, false);
	private final CurrencyRate eur =
		new CurrencyRate(Currency.EUR, 82.91f, true);

	@NonNull
	@Override
	public Flowable<List<CurrencyRate>> getCurrencyRates() {
		return Flowable.just(Arrays.asList(usd, eur));
	}

	@NonNull
	@Override
	public Flowable<CurrencyRate> getSystemCurrencyRate() {
		return Flowable.just(usd);
	}
}
