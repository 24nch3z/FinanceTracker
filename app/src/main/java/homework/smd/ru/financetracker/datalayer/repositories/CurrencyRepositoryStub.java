package homework.smd.ru.financetracker.datalayer.repositories;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import homework.smd.ru.financetracker.datalayer.CurrencyRepository;
import homework.smd.ru.financetracker.datalayer.data.network.CurrencyAPI;
import homework.smd.ru.financetracker.datalayer.data.network.CurrencyService;
import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.CurrencyModelAPI;
import homework.smd.ru.financetracker.models.CurrencyRate;
import io.reactivex.Flowable;

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
	public Flowable<CurrencyModelAPI> getCurrencyRates() {
		final Flowable<CurrencyModelAPI> usd = currencyService.updateCurrency("USD_RUB");
		final Flowable<CurrencyModelAPI> eur = currencyService.updateCurrency("EUR_RUB");

		// TODO save to cache
		return Flowable
			.merge(usd, eur);
	}

	@NonNull
	@Override
	public Flowable<CurrencyRate> getSystemCurrencyRate() {
		return Flowable.just(usd);
	}
}
