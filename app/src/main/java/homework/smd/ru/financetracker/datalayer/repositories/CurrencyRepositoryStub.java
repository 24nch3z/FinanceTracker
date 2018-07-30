package homework.smd.ru.financetracker.datalayer.repositories;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import homework.smd.ru.financetracker.datalayer.CurrencyRepository;
import homework.smd.ru.financetracker.datalayer.data.network.CurrencyAPI;
import homework.smd.ru.financetracker.datalayer.data.network.CurrencyService;
import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.CurrencyRate;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class CurrencyRepositoryStub implements CurrencyRepository {

	@Inject CurrencyService currencyService;

	private final CurrencyRate usd =
		new CurrencyRate(Currency.USD, 68.56f);
	private final CurrencyRate eur =
		new CurrencyRate(Currency.EUR, 82.91f);

	public CurrencyRepositoryStub() {
		currencyService = new CurrencyAPI().getCurrencyService();
	}

	@NonNull
	@Override
	public Flowable<String> getCurrencyRates() {
		final Flowable<Object> usd = currencyService.updateCurrency("USD_RUB", "y");
		final Flowable<Object> eur = currencyService.updateCurrency("EUR_RUB", "y");

		// TODO save to cache
		return Flowable
			.merge(usd, eur)
			.map(Object::toString)
			.subscribeOn(Schedulers.io());
	}

	@NonNull
	@Override
	public Flowable<CurrencyRate> getSystemCurrencyRate() {
		return Flowable.just(usd);
	}
}
